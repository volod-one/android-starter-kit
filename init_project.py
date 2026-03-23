import os
import shutil
import sys
import re

# ---- CONFIG ----
OLD_BASE_PACKAGE = "com.example"
OLD_APP_PACKAGE = "com.example.starterkit"
OLD_APP_NAME = "StarterKit"

TEXT_EXTENSIONS = ('.kt', '.kts', '.xml', '.properties', '.md')
EXCLUDE_DIRS = {'.git', 'build', '.gradle', '.idea'}

# ---- HELPERS ----
def should_skip(path: str) -> bool:
    return any(skip in path for skip in EXCLUDE_DIRS)


def replace_package(content: str, old: str, new: str) -> str:
    # Replace only exact package prefix (safe)
    pattern = r'\b' + re.escape(old) + r'\b'
    return re.sub(pattern, new, content)


def normalize_name(name: str) -> str:
    # MyTestApp → mytestapp
    return name.lower()

# ---- CONTENT RENAME ----
def rename_content(
        root_dir,
        old_base,
        new_base,
        old_app_pkg,
        new_app_pkg,
        old_name,
        new_name,
        should_update_package
):
    print("🔧 Updating file contents...")

    for root, dirs, files in os.walk(root_dir):
        if should_skip(root):
            continue

        for file in files:
            if file.endswith(TEXT_EXTENSIONS):
                path = os.path.join(root, file)

                try:
                    with open(path, 'r', encoding='utf-8', errors='ignore') as f:
                        content = f.read()

                    new_content = content

                    # Replace packages only if needed
                    if should_update_package:
                        # 1. Replace app package FIRST
                        new_content = replace_package(new_content, old_app_pkg, new_app_pkg)

                        # 2. Replace base package
                        new_content = replace_package(new_content, old_base, new_base)

                    # Replace app name always
                    new_content = new_content.replace(old_name, new_name)

                    if new_content != content:
                        with open(path, 'w', encoding='utf-8') as f:
                            f.write(new_content)
                        print(f"  ✔ Updated: {path}")

                except Exception as e:
                    print(f"  ⚠️ Skipped: {path} -> {e}")


# ---- FILE RENAME ----
def rename_files(root_dir, old_name, new_name):
    print("📁 Renaming files...")

    for root, dirs, files in os.walk(root_dir):
        if should_skip(root):
            continue

        for file in files:
            if old_name in file:
                old_path = os.path.join(root, file)
                new_path = os.path.join(root, file.replace(old_name, new_name))

                os.rename(old_path, new_path)
                print(f"  ✔ Renamed: {old_path} -> {new_path}")


# ---- PACKAGE MOVE ----
def move_package_dirs(
        root_dir,
        old_base,
        new_base,
        old_app_pkg,
        new_app_pkg,
        should_update_package
):
    if not should_update_package:
        print("📦 Skipping package move (package unchanged)")
        return

    print("📦 Moving package directories...")

    old_base_parts = old_base.split(".")
    new_base_parts = new_base.split(".")

    old_app_parts = old_app_pkg.split(".")
    new_app_parts = new_app_pkg.split(".")

    for root, dirs, files in os.walk(root_dir):
        if should_skip(root):
            continue

        if root.endswith("src/main/java") or \
                root.endswith("src/test/java") or \
                root.endswith("src/androidTest/java"):

            # Detect app module
            is_app_module = os.path.sep + "app" + os.path.sep in root

            if is_app_module:
                old_path = os.path.join(root, *old_app_parts)
                new_path = os.path.join(root, *new_app_parts)
            else:
                old_path = os.path.join(root, *old_base_parts)
                new_path = os.path.join(root, *new_base_parts)

            if not os.path.exists(old_path):
                continue

            print(f"  ✔ Moving: {old_path} -> {new_path}")

            os.makedirs(new_path, exist_ok=True)

            for sub_root, _, sub_files in os.walk(old_path):
                rel = os.path.relpath(sub_root, old_path)
                target_dir = os.path.join(new_path, rel)
                os.makedirs(target_dir, exist_ok=True)

                for file in sub_files:
                    shutil.move(
                        os.path.join(sub_root, file),
                        os.path.join(target_dir, file)
                    )

            # remove old base package folder
            shutil.rmtree(old_path, ignore_errors=True)


# ---- CLEANUP ----
def cleanup_git():
    if os.path.exists(".git"):
        shutil.rmtree(".git")
        print("🗑️ Removed .git directory")

    os.system("git init > /dev/null 2>&1")
    print("🔄 Initialized new git repository")


# ---- MAIN ----
def main():
    if len(sys.argv) >= 3:
        new_base_package = sys.argv[1].strip()
        new_app_name = sys.argv[2].strip()
    else:
        new_base_package = input("Enter base package (leave empty to keep current): ").strip()
        new_app_name = input("Enter project name: ").strip()

    if not new_base_package:
        new_base_package = OLD_BASE_PACKAGE

    should_update_package = new_base_package != OLD_BASE_PACKAGE

    normalized_name = normalize_name(new_app_name)
    new_app_package = f"{new_base_package}.{normalized_name}"

    print(f"\n🚀 Setting up project:")
    print(f"   Base package: {new_base_package}")
    print(f"   App name:     {new_app_name}")
    print(f"   App package:  {new_app_package}")

    if not should_update_package:
        print("ℹ️ Package unchanged — only renaming app\n")
    else:
        print()

    rename_content(
        ".",
        OLD_BASE_PACKAGE,
        new_base_package,
        OLD_APP_PACKAGE,
        new_app_package,
        OLD_APP_NAME,
        new_app_name,
        should_update_package
    )

    rename_files(".", OLD_APP_NAME, new_app_name)

    move_package_dirs(
        ".",
        OLD_BASE_PACKAGE,
        new_base_package,
        OLD_APP_PACKAGE,
        new_app_package,
        should_update_package
    )

    cleanup_git()

    # Optional self-delete
    confirm = input("\nDelete setup script? (y/n): ")
    if confirm.lower() == "y":
        try:
            os.remove(sys.argv[0])
            print("🪄 Script removed")
        except Exception as e:
            print(f"⚠️ Could not delete script: {e}")

    print("\n✅ Project setup complete!")


if __name__ == "__main__":
    main()
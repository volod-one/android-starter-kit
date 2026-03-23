# Android Starter Kit

A professional, multi-module Android project template designed to eliminate the repetitive "New Project" grind. This kit provides a battle-tested architecture and a dedicated Python script to handle the heavy lifting of project initialization.

## Purpose
The goal of this Starter Kit is to move from "File > New Project" to writing business logic in seconds. It provides a pre-configured environment with **Clean Architecture** and **Multi-module** support, so you don't have to set up your core layers manually every time.

---

## Architecture
The template is divided into three core pillars to ensure scalability and testability:

* **`:core`** — Infrastructure and shared utilities (Network, Database, Design System).
* **`:domain`** — The Source of Truth. Contains pure Kotlin business logic, Models, and Repository interfaces.
* **`:data`** — The Implementation. Handles API calls (Ktor/Retrofit), Room persistence, and data mapping.
* **`:app`** — The entry point that wires everything together.

---

## How to Initialize
This repository includes an `init_project.py` script. This script acts as a "surgeon" that rebrands the template into your specific app.

### Prerequisites
* Python 3.x installed on your machine.

### Steps
1.  **Clone** this repository to your local machine.
2.  **Navigate** to the root directory.
3.  **Run the script**:
    ```bash
    python3 init_project.py "com.your.package" "YourAppName"
    ```
    or
    ```bash
    python3 init_project.py
    ```

### What the script does:
* **Renames Package IDs**: Replaces the template package name with yours in all `.kt`, `.kts`, and `.xml` files.
* **Reconstructs Directories**: Physically moves files into the correct folder structure (e.g., from `com/example` to `com/my/app`).
* **Rebrands the App**: Updates the App Name in resources and manifests.
* **Wipes History**: Removes the template's `.git` history and initializes a fresh repository.
* **Cleans Up**: Offers to delete itself after execution, leaving you with a "clean" project.

---

## 🛠 Tech Stack (Baseline)
* **Language:** Kotlin
* **UI:** Jetpack Compose
* **Async:** Coroutines & Flow
* **Networking:** Pre-configured Ktor/Network Layer
* **DI:** Hilt (ready for injection)

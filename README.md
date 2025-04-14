# GFM Recurring Donations CLI

Command-line application to manage recurring donations for GoFundMe campaigns.  
Supports donor limits, donation commands, and outputs monthly summaries.

---

## How to Run

### Prerequisites

- Java 21 or newer
- A UNIX-like shell (Linux/macOS) or Git Bash on Windows
- `make` installed (on UNIX)
- Alternatively, you can run using the provided script

---

### Build & Run

#### On UNIX/macOS (Recommended)

```bash
# Compile the project
make

# Run interactively
make run

# Run with a file
make run-input
```

You can also run using the CLI script:

```bash
chmod +x gfm-recurring
./gfm-recurring            # Interactive mode
./gfm-recurring input.txt  # File mode
cat input.txt | ./gfm-recurring  # Pipe mode
```

#### On Windows (Git Bash or WSL recommended)

```bash
# Compile
javac -d bin $(find src -name "*.java")

# Run
java -cp bin main.Application
java -cp bin main.Application input.txt
```

---

## Input Format

```text
Add Donor Greg $1000
Add Campaign SaveTheDogs
Donate Greg SaveTheDogs $100
```

Supported commands:

- `Add Donor [name] $[monthlyLimit]`
- `Add Campaign [name]`
- `Donate [donorName] [campaignName] $[amount]`
- `Delete Donor [name]`
- `Delete Campaign [name]`
- `Print_summary`
- `Print_donations`
- `Help`
- `Exit`

---

## Sample Run

Given `input.txt`:

```text
Add Donor Greg $1000
Add Donor Janine $100
Add Campaign SaveTheDogs
Add Campaign HelpTheKids
Donate Greg SaveTheDogs $100
Donate Greg HelpTheKids $200
Donate Janine SaveTheDogs $50
```

Execution:

```bash
cat input.txt | ./gfm-recurring
```

Output:

```text
Donors:
Greg: $300 Average: $150
Janine: $50 Average: $50

Campaigns:
HelpTheKids: Total: $200
SaveTheDogs: Total: $150
```

---

## Running Tests

```bash
make test
```

This will compile the project and run all unit tests via `TestRunner.java`.

---

## Project Structure

- `src/`: all Java source code
- `bin/`: compiled `.class` files
- `gfm-recurring`: shell script for execution
- `input.txt`: sample input
- `Makefile`: build/run automation

---

## Design Rationale

This solution was designed with maintainability, testability, and clarity in mind. The system is structured using the Model-View-Controller (MVC) architectural pattern:

- **Model**: Includes "Donor", "Campaign", and "Donation", each encapsulating domain logic.
- **View**: The "ConsoleView" class is responsible for input/output handling.
- **Controller**: The "CommandController" interprets input commands and delegates to services.

All business logic is implemented in "DonorService", "CampaignService", and "DonationService". Each of these is injected into the controller via constructor-based dependency injection.

Persistence is handled manually using CSV files and corresponding repositories, avoiding any use of external libraries. This aligns with the constraints of a shell-only evaluation environment.

CommandController processes all comands and handles errores

Every effort was made to maintain separation of concerns and single responsibility per class. The codebase is modular and could easily be extended to support additional commands, features, or even a different UI (e.g., GUI or web-based interface).

No external libraries were used — only standard Java

---

## Notes

- An example `input.txt` file is provided.
- All donor donation limits are enforced.
- Invalid or excessive donations are gracefully ignored.
- If run with an input file, the program will enter interactive mode after processing it.
- If run with piped data, the program will process the commands and then exit. This is because `stdin` was causing issues when reading the commands

---

## Final Words

This program was crafted to meet all criteria provided in the challenge instructions, with extra attention to code clarity, usability, and testing.  
Please feel free to reach out for any clarification.
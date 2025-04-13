GFM RECURRING - RECURRING DONATION SYSTEM
=========================================

This project is a command-line Java application that implements a simplified recurring donation engine. It accepts commands for managing donors, campaigns, and donations, and it maintains a persistent state across runs using CSV files. The application follows the MVC architectural pattern with manual dependency injection and file-based persistence.

--------------------------------------------------------------------------------
1. How to Build and Run the Solution
--------------------------------------------------------------------------------

REQUIREMENTS:
- A Unix-like environment with a shell and terminal.
- A Java Development Kit (JDK) version 11 or above.

STEPS TO BUILD:
1. Open a terminal and navigate to the root directory of the project.
2. Add execution permissions to the file named "gfm-recurring" running the following command:

    sudo chmod +x gfm-recurring

3. When this file is executed, it will automatically compile the program. It will create the "out/" folder containing the compiled ".class" files
4. Otherwise, if the examiner wants to manually build the program, the following command should be executed:
  
    javac -d out src/**/*.java 

5. This will create the "out/" folder where the "*.class" files are located.

STEPS TO EXECUTE:
1. Run the program by executing:

    ./gfm-recurring [filename].txt
                OR
    cat [filename].txt | ./gfm-recurring

2. This will run the main application and feed it the input file.
3. The program will read all instructions from the file and produce the output to the console.
4. After execution, three CSV files will be generated (or updated) under a "data/" directory:

    - "donors.csv"
    - "campaigns.csv"
    - "donations.csv"

--------------------------------------------------------------------------------
2. How to Test the Solution
--------------------------------------------------------------------------------

The correctness of the solution can be tested by comparing its output against a predefined expected output.

EXPECTED TESTING STEPS:
- Place the provided "input.txt" in the root directory. 
- Run the application using "./gfm-recurring [filename].txt" or "cat [filename].txt | ./gfm-recurring".
- Compare the output on the console to the specified expected output.

The evaluator may also modify "input.txt" with new commands or test edge cases such as:
- Donations exceeding monthly limits.
- Adding duplicate donors or campaigns.
- Missing or malformed input lines.

After each run, check the contents of the "data/" folder to validate that CSV files were correctly written.

--------------------------------------------------------------------------------
3. Implementation Process and Rationale
--------------------------------------------------------------------------------

This solution was designed with maintainability, testability, and clarity in mind. The system is structured using the Model-View-Controller (MVC) architectural pattern:

- **Model**: Includes "Donor", "Campaign", and "Donation", each encapsulating domain logic.
- **View**: The "ConsoleView" class is responsible for input/output handling.
- **Controller**: The "CommandController" interprets input commands and delegates to services.

All business logic is implemented in "DonorService", "CampaignService", and "DonationService". Each of these is injected into the controller via constructor-based dependency injection.

Persistence is handled manually using CSV files and corresponding repositories, avoiding any use of external libraries. This aligns with the constraints of a shell-only evaluation environment.

Every effort was made to maintain separation of concerns and single responsibility per class. The codebase is modular and could easily be extended to support additional commands, features, or even a different UI (e.g., GUI or web-based interface).

--------------------------------------------------------------------------------
4. Execution Summary
--------------------------------------------------------------------------------

After running the application with "input.txt", the output will include:
- The echoed input.
- A formatted summary of donor totals and averages.
- A list of campaign totals.

All output will be printed to standard output.


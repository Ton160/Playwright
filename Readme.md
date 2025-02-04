Playwright Java Automation Project
Overview
This project demonstrates automation for web interactions using Playwright in Java. It covers various use cases such as handling modal dialogs, alerts, popups, form filling, and assertions, making it a useful foundation for automation testing and web scraping tasks.

Features
Navigate Pages: Load and interact with web pages.
Modal Dialog Handling: Open, verify, and close modal dialogs.
Alert & Popup Handling: Interact with JavaScript alerts, confirms, and popups.
Assertions: Verify visibility and other properties of web elements.
Screenshots: Capture screenshots on failure or for debugging purposes.
Element Interaction: Use locators to perform actions such as clicking, filling inputs, and waiting for elements.
Prerequisites
Before running the project, ensure you have the following installed:

JDK 11+ (Java Development Kit)
Maven (for dependency management)
Playwright for Java (and Playwright’s dependencies)
Setup
1. Clone the Repository
   bash
   نسخ
   تحرير
   git clone https://github.com/yourusername/playwright-java-automation.git
   cd playwright-java-automation
2. Install Dependencies
   If you're using Maven, add the Playwright dependency in the pom.xml file:

xml
نسخ
تحرير
<dependency>
<groupId>com.microsoft.playwright</groupId>
<artifactId>playwright</artifactId>
<version>1.20.0</version>
</dependency>
Run the following command to install all dependencies:

bash
نسخ
تحرير
mvn clean install
3. Running the Project
   To run the project, execute the LocatorsActions class:

bash
نسخ
تحرير
mvn exec:java -Dexec.mainClass="LocatorsActions"
Alternatively, run it directly from your IDE by executing the main method in LocatorsActions.java.

Example Usage
In the example provided, the script does the following:

Navigates to a Modal Demo Page: Opens a webpage with a modal dialog.
Clicks on the Modal Button: Simulates a click on the button that opens the modal.
Verifies Modal Visibility: Waits for the modal to appear and confirms it.
Closes the Modal: Clicks the close button to close the modal.
Takes a Screenshot: Captures a screenshot if necessary.
Code Snippet
java
نسخ
تحرير
// Example of opening a modal and closing it
page.locator("button[data-bs-target='#exampleModal']").click();
Locator modal = page.locator("#exampleModal");
modal.waitFor();
System.out.println("✅ The modal appeared successfully!");

// Close the modal
Locator closeButton = page.locator("#exampleModal > div > div > div.modal-footer > button.btn.btn-secondary");
if (closeButton.isVisible()) {
closeButton.click();
System.out.println("❌ The modal was closed.");
} else {
System.out.println("❌ The close button is not visible.");
}
Contributing
We welcome contributions to improve the project. To contribute:

Fork this repository.
Create a new branch (git checkout -b feature-branch).
Commit your changes (git commit -am 'Add new feature').
Push to the branch (git push origin feature-branch).
Open a Pull Request.
License
This project is licensed under the MIT License - see the LICENSE file for details.


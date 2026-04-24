package Main;

import Factory.LoanFactory;
import Payable.InputValidator;
import bkLoan.Customer;
import bkLoan.LoanManager;

import java.util.Scanner;

public class LoanSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {


            // CUSTOMER DETAILS

            System.out.println(" ENTER CUSTOMER DETAILS");
            System.out.println("\n________________________");

            String customerId;

            while (true) {
                System.out.print("Customer ID: ");
                customerId = scanner.nextLine();
                try {
                    InputValidator.validateNotEmpty(customerId, "Customer ID");
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            String customerName;
            while (true) {
                System.out.print("Customer Name: ");
                customerName = scanner.nextLine();
                try {
                    InputValidator.validateNotEmpty(customerName, "Customer Name");
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            String nationalId;
            while (true) {
                System.out.print("National ID: ");
                nationalId = scanner.nextLine();
                try {
                    InputValidator.validateNotEmpty(nationalId, "National ID");
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            String phone;
            while (true) {
                System.out.print("Phone Number: ");
                phone = scanner.nextLine();
                try {
                    InputValidator.validateNotEmpty(phone, "Phone");
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            Customer customer = new Customer(customerId, customerName, nationalId, phone);


            // LOAN DETAILS

            System.out.println("\n ENTER LOAN DETAILS");
            System.out.println("\n____________________");

            String loanType;
            while (true) {
                System.out.print("Loan Type (personal/home/car/business/student/agricultural): ");
                loanType = scanner.nextLine();
                try {
                    InputValidator.validateNotEmpty(loanType, "Loan Type");
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            String loanId;
            while (true) {
                System.out.print("Loan ID: ");
                loanId = scanner.nextLine();
                try {
                    InputValidator.validateNotEmpty(loanId, "Loan ID");
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            double amount;
            while (true) {
                System.out.print("Loan Amount: ");
                try {
                    amount = Double.parseDouble(scanner.nextLine());
                    InputValidator.validatePositiveNumber(amount, "Loan Amount");
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input. Enter a valid number greater than 0.");
                }
            }

            double rate;
            while (true) {
                System.out.print("Interest Rate (%): ");
                try {
                    rate = Double.parseDouble(scanner.nextLine());
                    InputValidator.validateInterestRate(rate);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            int duration;
            while (true) {
                System.out.print("Loan Duration (months): ");
                try {
                    duration = Integer.parseInt(scanner.nextLine());
                    InputValidator.validatePositiveNumber(duration, "Duration");
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid duration. Must be a number > 0.");
                }
            }

            System.out.print("Officer Name: ");
            String officer = scanner.nextLine();

            System.out.print("Branch Location: ");
            String branch = scanner.nextLine();


            // CREATE LOAN USING FACTORY

            LoanManager loan = LoanFactory.createLoan(
                    loanType,
                    loanId,
                    amount,
                    rate,
                    duration,
                    officer,
                    branch
            );


            // DISPLAY DETAILS

            System.out.println("\n CUSTOMER DETAILS ");
            System.out.println("\n___________________");
            System.out.println(customer);

            System.out.println("\n LOAN DETAILS");
            System.out.println("\n______________");
            System.out.println(loan);


            // LOAN VALIDATION + APPROVAL

            if (loan.validateLoanDetails() &&
                    loan.checkEligibility(1000, 650)) {
                loan.approveLoan();
                System.out.println("\nLoan Approved!");
            } else {
                loan.rejectLoan();
                System.out.println("\nLoan Rejected!");
            }


            // PAYMENT SECTION

            System.out.println("\n PAYMENT SECTION");
            System.out.println("\n__________________");

            double payment;
            while (true) {
                System.out.print("Enter payment amount: ");
                try {
                    payment = Double.parseDouble(scanner.nextLine());
                    InputValidator.validatePositiveNumber(payment, "Payment");
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid payment. Try again.");
                }
            }

            loan.processPayment(payment);

            System.out.println("\n REPAYMENT SUMMARY");
            System.out.println("\n____________________");
            System.out.println("Remaining Balance: " + loan.calculateRemainingBalance());
            System.out.println(loan.generatePaymentReceipt(payment));

            System.out.println("\n FINAL LOAN REPORT");
            System.out.println("\n___________________");
            System.out.println(loan.generateLoanReport());

        } catch (Exception e) {
            System.out.println("SYSTEM ERROR: " + e.getMessage());
        } finally {
            System.out.println("\nSystem Closed.");
        }
    }
}
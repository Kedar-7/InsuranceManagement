package main;
import Dao.InsuranceServiceImplements;
import Entity.Policy;
import Util.DBConnUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Collection;
import java.util.Scanner;

public class MainModule {

 private static final Scanner scanner = new Scanner(System.in);
 private static final InsuranceServiceImplements PolicyService = new InsuranceServiceImplements();

 public static void main(String[] args) {
     try {
         
         Connection connection = DBConnUtil.getConnection();
         if (connection != null) {
             System.out.println("Database connection established.");

           
             while (true) {
                 System.out.println("\n***** Insurance Management System *****");
                 System.out.println("1. Create Policy");
                 System.out.println("2. Get Policy");
                 System.out.println("3. Get All Policies");
                 System.out.println("4. Update Policy");
                 System.out.println("5. Delete Policy");
                 System.out.println("6. Exit");
                 System.out.print("Enter your choice: ");

                 int choice = scanner.nextInt();
                 scanner.nextLine(); 

                 switch (choice) {
                     case 1:
                         createPolicy();
                         break;
                     case 2:
                         getPolicy();
                         break;
                     case 3:
                         getAllPolicies();
                         break;
                     case 4:
                         updatePolicy();
                         break;
                     case 5:
                         deletePolicy();
                         break;
                     case 6:
                         System.out.println("Exiting the program.........");
                         System.exit(0);
                     default:
                         System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                 }
             }
         } else {
             System.out.println("Failed to establish a database connection. Exiting...");
         }
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         scanner.close();
     }
 }

 private static void createPolicy() {
	
     try {
        
    	 System.out.println("Enter policy id: ");
         int policyId = scanner.nextInt();
         
         scanner.nextLine();
         
         System.out.println("Enter Policy Type:");
         String policyType = scanner.nextLine();
         
         System.out.println("Enter Policy Description:");
         String policyDescription = scanner.nextLine();
         
         System.out.println("Enter Coverage Amount:");
         BigDecimal coverageAmount = scanner.nextBigDecimal();

   
         Policy policy = new Policy(policyId, policyType, policyDescription, coverageAmount);

         
         boolean success = PolicyService.createPolicy(policy);

         if (success) {
             System.out.println("Policy created successfully.");
         } else {
             System.out.println("Failed to create the policy.");
         }
     } catch (Exception e) {
         handleException(e);
     }
 }

 private static void getPolicy() {
     try {
        
         System.out.println("Enter Policy ID to retrieve:");
         int policyId = scanner.nextInt();

        
         Policy policy = PolicyService.getPolicy(policyId);

         System.out.println("Retrieved Policy: " + policy);
     }  catch (Exception e) {
         handleException(e);
     }
 }

 private static void getAllPolicies() {
     try {
        
         Collection<Policy> policies = PolicyService.getAllPolicies();

         
         System.out.println("All Policies:");
         for (Object policy : policies) {
             System.out.println(policy);
         }
     } catch (Exception e) {
         handleException(e);
     }
 }

 private static void updatePolicy() {
     try {
         
         System.out.println("Enter Policy ID to update:");
         int policyId = scanner.nextInt();

         scanner.nextLine(); 

         System.out.println("Enter Updated Policy Type:");
         String updatedPolicyType = scanner.nextLine();
         
         System.out.println("Enter Updated Policy Description:");
         String updatedPolicyDescription = scanner.nextLine();
         
         System.out.println("Enter Updated Coverage Amount:");
         BigDecimal updatedCoverageAmount = scanner.nextBigDecimal();

         
         Policy updatedPolicy = new Policy(policyId, updatedPolicyType, updatedPolicyDescription, updatedCoverageAmount);

         
         boolean success = PolicyService.updatePolicy(updatedPolicy);

         if (success) {
             System.out.println("Policy updated successfully.");
         } else {
             System.out.println("Failed to update the policy.");
         }
     } catch (Exception e) {
         handleException(e);
     }
 }

 private static void deletePolicy() {
     try {
         
         System.out.println("Enter Policy ID to delete:");
         int policyId = scanner.nextInt();

         
         boolean success = PolicyService.deletePolicy(policyId);

         if (success) {
             System.out.println("Policy deleted successfully.");
         } else {
             System.out.println("Failed to delete the policy.");
         }
     } catch (Exception e) {
         handleException(e);
     }
 }

 private static void handleException(Exception e) {
     System.out.println("An error occurred: " + e.getMessage());
     e.printStackTrace();
 }
}

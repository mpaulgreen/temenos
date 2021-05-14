package temenos;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntimeFactory;
import org.kie.dmn.api.core.*;

public class TemenosPrototype {
    public static void main(String[] args) {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        DMNRuntime dmnRuntime = KieRuntimeFactory.of(kieContainer.getKieBase()).get(DMNRuntime.class);
        int bankingYearsResult = 0;
        int creditScoreResult = 0;
        int yearsOfOperationResult = 0;

        // For BankingYearsParameterized
        String namespace = "https://kiegroup.org/dmn/_9D9A3A95-438A-4B2B-9EB1-ED0797B35BCD";
        String modelName = "BankingYearsParameterized";
        DMNModel dmnModel = dmnRuntime.getModel(namespace, modelName);
        DMNContext dmnContext = dmnRuntime.newContext();
        dmnContext.set("BankingYears", 3);
        dmnContext.set("BankingYearsThreshold 1", 5);
        dmnContext.set("BankingYearsThreshold 2", 10);
        dmnContext.set("BankingYearsThreshold 3", 15);
        dmnContext.set("BankingYearsGrade1", 5);
        dmnContext.set("BankingYearsGrade2", 10);
        dmnContext.set("BankingYearsGrade3", 15);
        dmnContext.set("BankingYearsGrade4", 20);
        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);

        for (DMNDecisionResult dr : dmnResult.getDecisionResults()) {
            System.out.println("Decision: '" + dr.getDecisionName() + "', " + "Result: " + dr.getResult());
            bankingYearsResult = Integer.parseInt(dr.getResult().toString()); // for quick prototyping
        }

        // For Credit Score Parameterized
        namespace = "https://kiegroup.org/dmn/_EF8F583F-F7E9-4260-9081-C974B36C91AC";
        modelName = "CreditScoreParameterized";
        dmnModel = dmnRuntime.getModel(namespace, modelName);
        dmnContext = dmnRuntime.newContext();
        dmnContext.set("Credit Score", 3);
        dmnContext.set("Credit Score Threshold 1", 5);
        dmnContext.set("Credit Score Threshold 2", 10);
        dmnContext.set("Credit Score Threshold 3", 15);
        dmnContext.set("Credit Score Grade 1", 5);
        dmnContext.set("Credit Score Grade 2", 10);
        dmnContext.set("Credit Score Grade 3", 15);
        dmnContext.set("Credit Score Grade 4", 20);
        dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);

        for (DMNDecisionResult dr : dmnResult.getDecisionResults()) {
            System.out.println("Decision: '" + dr.getDecisionName() + "', " + "Result: " + dr.getResult());
            creditScoreResult = Integer.parseInt(dr.getResult().toString()); // for quick prototyping
        }

        // For Years of operation parameterized
        namespace = "https://kiegroup.org/dmn/_FCC9F0EA-2E6E-4E63-A97C-B6C1A1359A94";
        modelName = "YearsOfOperation";
        dmnModel = dmnRuntime.getModel(namespace, modelName);
        dmnContext = dmnRuntime.newContext();
        dmnContext.set("OperationYears", 3);
        dmnContext.set("OperationYearsThreshold 1", 5);
        dmnContext.set("OperationYearsThreshold 2", 10);
        dmnContext.set("OperationYearsThreshold 3", 15);
        dmnContext.set("OperationYearsGrade1", 0);
        dmnContext.set("OperationYearsGrade2", 5);
        dmnContext.set("OperationYearsGrade3", 10);
        dmnContext.set("OperationYearsGrade4", 25);
        dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);

        for (DMNDecisionResult dr : dmnResult.getDecisionResults()) {
            System.out.println("Decision: '" + dr.getDecisionName() + "', " + "Result: " + dr.getResult());
            yearsOfOperationResult = Integer.parseInt(dr.getResult().toString()); // for quick prototyping
        }

        // For Final Grade
        namespace = "https://kiegroup.org/dmn/_FB7D099B-11B0-4783-A9F4-29CD9AE8086E";
        modelName = "FinalGrade";
        dmnModel = dmnRuntime.getModel(namespace, modelName);
        dmnContext = dmnRuntime.newContext();
        dmnContext.set("CreditScoreGrade", creditScoreResult);
        dmnContext.set("BankingYearsGrade", bankingYearsResult);
        dmnContext.set("YearsOfOperationGrade", yearsOfOperationResult);
        dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);

        for (DMNDecisionResult dr : dmnResult.getDecisionResults()) {
            System.out.println("Decision: '" + dr.getDecisionName() + "', " + "Final Grade: " + dr.getResult());

        }
    }
}
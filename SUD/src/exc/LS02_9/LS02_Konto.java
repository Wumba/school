package exc.LS02_9;

import exc.utils.NumberInputHandler;
import exc.utils.UserInteractionViaConsole;

public class LS02_Konto implements UserInteractionViaConsole {
    NumberInputHandler numberInputHandler = new NumberInputHandler();
    double overdraft;
    double currentAssets = 1000.00;

    private void setOverdraft(double overdraft) {
        if (overdraft > 0) {
            System.out.println("Can't be positive.");
        } else {
            this.overdraft = overdraft;
        }
    }

    @Override
    public void speakWithUser() {
        boolean contiuneInput = true;

        System.out.println("Welcome to GIJA Bank");
        System.out.println("Start Balance: " + this.currentAssets);
        System.out.println("Enter the overdraft credit  for your account");

        do {
            this.setOverdraft(numberInputHandler.readDoubleFromConsoleUntilValid());
        } while (this.overdraft == 0);

        System.out.println("Overdraft credit: " + this.overdraft);
        while (contiuneInput ) {
            System.out.println("How much money do you want to withdraw?");
            double widthDraw = numberInputHandler.readDoubleFromConsoleUntilValid();
            calculateAndBookWithdraw(widthDraw);
            if (overdraft == currentAssets){
                contiuneInput = false;
            } else {
                contiuneInput = numberInputHandler.checkRunAgain();
            }
        }
        System.out.println("Thanks for using GIJA Bank. Have a nice day!");
        numberInputHandler.closeReader();
    }

    private void calculateAndBookWithdraw(double withdraw) {
        if (withdraw > 0) {
            double result = this.currentAssets - withdraw;
            if (result >= this.overdraft) {
                this.currentAssets = numberInputHandler.roundDouble(result, 2);
                System.out.println("New balance:  " + this.currentAssets);
            } else {
                double moneyLeft = this.currentAssets - this.overdraft;
                moneyLeft = numberInputHandler.roundDouble(moneyLeft, 2);
                System.out.println("You reached your limit! Try an amount smaller than " + moneyLeft);
            }
        } else {
            System.out.println("You are funny, you can't withdraw negative money!");
        }
    }


    public static void main(String[] args) {
        LS02_Konto ls02_konto = new LS02_Konto();
        ls02_konto.speakWithUser();
    }


}

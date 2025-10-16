import Notifications.*;
import Payments.*;
import Payments.OnlinePayments.CreditCardPaymentMethod;
import Payments.OnlinePayments.OnlinePaymentMethod;
import Payments.OnlinePayments.PaypalPaymentMethod;

public class Driver {
    public static void main(String[] args) {
        System.out.println("======== PART 1 ========");

        Channel email = new EmailChannel();
        OnlinePaymentMethod creditCardMethod = new CreditCardPaymentMethod();
        Payment creditCardWithEmail = new OnlinePaymentNotification(email, creditCardMethod);
        
        System.out.println("Scenario 1: " + creditCardWithEmail.toString());
        
        OnlinePaymentMethod paypalMethod = new PaypalPaymentMethod();
        Payment paypalWithEmail = new OnlinePaymentNotification(email, paypalMethod);
        
        System.out.println("Scenario 2: " + paypalWithEmail.toString());

        Channel sms = new SmsChannel();

        OnlinePaymentMethod creditCardMethod2 = new CreditCardPaymentMethod();
        Payment creditCardWithSms = new OnlinePaymentNotification(sms, creditCardMethod2);

        System.out.println("Scenario 3: " + creditCardWithSms.toString());
        
        OnlinePaymentMethod paypalMethod2 = new PaypalPaymentMethod();
        Payment paypalWithSms = new OnlinePaymentNotification(sms, paypalMethod2);
        
        System.out.println("Scenario 4: " + paypalWithSms.toString());

        Channel email2 = new EmailChannel();
        Payment cashWithEmail = new CashOnDeliveryPayment(email2);

        System.out.println("Scenario 5: " + cashWithEmail.toString());

        Channel sms2 = new SmsChannel();
        Payment cashWithSms = new CashOnDeliveryPayment(sms2);
        
        System.out.println("Scenario 6: " + cashWithSms.toString());

        System.out.println("======== PART 2 ========");

        Channel emailChannel3 = new EmailChannel();
        Payment bitcoinWithEmail = new BitcoinPayment(emailChannel3);

        System.out.println("Scenario 1: " + bitcoinWithEmail.toString());

        Channel sms3 = new SmsChannel();
        Payment bitcoinWithSms = new BitcoinPayment(sms3);

        System.out.println("Scenario 2: " + bitcoinWithSms.toString());
        
        System.out.println("======== PART 3 ========");

        Channel push = new PushChannel();
        Payment cashWithPush = new CashOnDeliveryPayment(push);

        System.out.println("Scenario 1: " + cashWithPush.toString());

        Channel push2 = new PushChannel();
        Payment bitcoinWithPush = new BitcoinPayment(push2);

        System.out.println("Scenario 2: " + bitcoinWithPush.toString());

    }
}

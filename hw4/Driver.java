package hw4;

import hw4.Notifications.*;
import hw4.Payments.*;

public class Driver {
    public static void main(String[] args) {
        System.out.println("======== PART 1 ========");
        System.out.println("=== Online Payment with Email Notification ===");

        Channel email = new EmailChannel();

        OnlinePaymentMethod creditCardMethod = new CreditCardPaymentMethod();
        OnlinePaymentMethod paypalMethod = new PaypalPaymentMethod();

        Payment creditCardWithEmail = new OnlinePaymentNotification(email, creditCardMethod);
        Payment paypalWithEmail = new OnlinePaymentNotification(email, paypalMethod);
        
        creditCardWithEmail.pay(100.0);
        paypalWithEmail.pay(200.0);

        System.out.println("\n=== Online Payment with SMS Notification ===");

        Channel sms = new SmsChannel();

        OnlinePaymentMethod creditCardMethod2 = new CreditCardPaymentMethod();
        OnlinePaymentMethod paypalMethod2 = new PaypalPaymentMethod();
        
        Payment creditCardWithSms = new OnlinePaymentNotification(sms, creditCardMethod2);
        Payment paypalWithSms = new OnlinePaymentNotification(sms, paypalMethod2);
        
        creditCardWithSms.pay(150.0);
        paypalWithSms.pay(250.0);

        System.out.println("\n=== Cash Payment with Email Notification ===");

        Channel email2 = new EmailChannel();

        Payment cashWithEmail = new CashOnDeliveryPayment(email2);

        cashWithEmail.pay(300.0);

        System.out.println("\n=== Cash Payment with SMS Notification ===");
        Channel sms2 = new SmsChannel();

        Payment cashWithSms = new CashOnDeliveryPayment(sms2);
        
        cashWithSms.pay(400.0);

        System.out.println("======== PART 2 ========");
        System.out.println("\n=== Bitcoin Payment with Email Notification ===");

        Channel emailChannel3 = new EmailChannel();

        Payment bitcoinWithEmail = new BitcoinPayment(emailChannel3);

        bitcoinWithEmail.pay(500.0);

        System.out.println("\n=== Bitcoin Payment with SMS Notification ===");

        Channel sms3 = new SmsChannel();

        Payment bitcoinWithSms = new BitcoinPayment(sms3);

        bitcoinWithSms.pay(600.0);
        
        System.out.println("======== PART 3 ========");
        System.out.println("\n=== Cash Payment with Push Notification ===");

        Channel push = new PushChannel();

        Payment cashWithPush = new CashOnDeliveryPayment(push);

        cashWithPush.pay(700.0);

        System.out.println("\n=== Bitcoin Payment with Push Notification ===");

        Channel push2 = new PushChannel();

        Payment bitcoinWithPush = new BitcoinPayment(push2);

        bitcoinWithPush.pay(800.0);

        System.out.println("\n=== Credit Card Payment with Push Notification ===");

        Channel push3 = new PushChannel();

        OnlinePaymentMethod creditCardMethod3 = new CreditCardPaymentMethod();

        Payment creditCardWithPush = new OnlinePaymentNotification(push3, creditCardMethod3);

        creditCardWithPush.pay(900.0);

    }
}

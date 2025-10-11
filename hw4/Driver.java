package hw4;

import hw4.Notifications.*;
import hw4.Payments.*;

public class Driver {
    public static void main(String[] args) {
        System.out.println("======== PART 1 ========");
        System.out.println("=== Online Payment with Email Notification ===");

        Channel emailChannel = new EmailChannel();

        PaymentMethod creditCardMethod = new CreditCardPaymentMethod();
        PaymentMethod paypalMethod = new PaypalPaymentMethod();

        Payment creditCardWithEmail = new OnlinePaymentNotification(emailChannel, creditCardMethod);
        Payment paypalWithEmail = new OnlinePaymentNotification(emailChannel, paypalMethod);
        
        creditCardWithEmail.pay(100.0);
        paypalWithEmail.pay(200.0);

        System.out.println("\n=== Online Payment with SMS Notification ===");

        Channel smsChannel = new SmsChannel();

        PaymentMethod creditCardMethod2 = new CreditCardPaymentMethod();
        PaymentMethod paypalMethod2 = new PaypalPaymentMethod();
        
        Payment creditCardWithSms = new OnlinePaymentNotification(smsChannel, creditCardMethod2);
        Payment paypalWithSms = new OnlinePaymentNotification(smsChannel, paypalMethod2);
        
        creditCardWithSms.pay(150.0);
        paypalWithSms.pay(250.0);

        System.out.println("\n=== Cash Payment with Email Notification ===");

        Channel emailChannel2 = new EmailChannel();

        PaymentMethod cashMethod = new CashPaymentMethod();

        Payment cashWithEmail = new CashOnDeliveryPayment(emailChannel2, cashMethod);

        cashWithEmail.pay(300.0);

        System.out.println("\n=== Cash Payment with SMS Notification ===");
        Channel smsChannel2 = new SmsChannel();

        PaymentMethod cashMethod2 = new CashPaymentMethod();

        Payment cashWithSms = new CashOnDeliveryPayment(smsChannel2, cashMethod2);
        
        cashWithSms.pay(400.0);

        System.out.println("======== PART 2 ========");
        System.out.println("\n=== Bitcoin Payment with Email Notification ===");

        Channel emailChannel3 = new EmailChannel();

        PaymentMethod bitcoinMethod = new BitcoinPaymentMethod();

        Payment bitcoinWithEmail = new OnlinePaymentNotification(emailChannel3, bitcoinMethod);

        bitcoinWithEmail.pay(500.0);

        System.out.println("\n=== Bitcoin Payment with SMS Notification ===");

        Channel smsChannel3 = new SmsChannel();

        PaymentMethod bitcoinMethod2 = new BitcoinPaymentMethod();

        Payment bitcoinWithSms = new OnlinePaymentNotification(smsChannel3, bitcoinMethod2);

        bitcoinWithSms.pay(600.0);
        
        System.out.println("======== PART 3 ========");
        System.out.println("\n=== Cash Payment with Push Notification ===");

        Channel pushChannel = new PushChannel();

        PaymentMethod cashMethod3 = new CashPaymentMethod();

        Payment cashWithPush = new CashOnDeliveryPayment(pushChannel, cashMethod3);

        cashWithPush.pay(700.0);

        System.out.println("\n=== Bitcoin Payment with Push Notification ===");

        Channel pushChannel2 = new PushChannel();

        PaymentMethod bitcoinMethod3 = new BitcoinPaymentMethod();

        Payment bitcoinWithPush = new OnlinePaymentNotification(pushChannel2, bitcoinMethod3);

        bitcoinWithPush.pay(800.0);

        System.out.println("\n=== Credit Card Payment with Push Notification ===");

        Channel pushChannel3 = new PushChannel();

        PaymentMethod creditCardMethod3 = new CreditCardPaymentMethod();

        Payment creditCardWithPush = new OnlinePaymentNotification(pushChannel3, creditCardMethod3);
        
        creditCardWithPush.pay(900.0);

    }
}

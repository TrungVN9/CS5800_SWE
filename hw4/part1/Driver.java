package hw4.part1;

import hw4.part1.Notifications.Channel;
import hw4.part1.Notifications.EmailChannel;
import hw4.part1.Notifications.SmsChannel;
import hw4.part1.Payments.CashOnDeliveryPayment;
import hw4.part1.Payments.CashPayment;
import hw4.part1.Payments.CreditCardPayment;
import hw4.part1.Payments.OnlinePaymentNotification;
import hw4.part1.Payments.Payment;
import hw4.part1.Payments.PaymentMethod;
import hw4.part1.Payments.PaypalPayment;

public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Online Payment with Email Notification ===");
        Channel emailChannel = new EmailChannel();

        // Create PaymentMethod implementations
        PaymentMethod creditCardMethod = new CreditCardPayment();
        PaymentMethod paypalMethod = new PaypalPayment();

        // Wrap PaymentMethods with an OnlinePaymentNotification (Payment + Channel)
        Payment creditCardWithEmail = new OnlinePaymentNotification(emailChannel, creditCardMethod);
        Payment paypalWithEmail = new OnlinePaymentNotification(emailChannel, paypalMethod);
        
        // Process Online Payments
        creditCardWithEmail.pay(100.0);
        paypalWithEmail.pay(200.0);

        System.out.println("\n=== Online Payment with SMS Notification ===");
        Channel smsChannel = new SmsChannel();

        PaymentMethod creditCardMethod2 = new CreditCardPayment();
        PaymentMethod paypalMethod2 = new PaypalPayment();
        
        Payment creditCardWithSms = new OnlinePaymentNotification(smsChannel, creditCardMethod2);
        Payment paypalWithSms = new OnlinePaymentNotification(smsChannel, paypalMethod2);
        
        creditCardWithSms.pay(150.0);
        paypalWithSms.pay(250.0);

        System.out.println("\n=== Cash Payment with Email Notification ===");
        Channel emailChannel2 = new EmailChannel();
        PaymentMethod cashMethod = new CashPayment();
        Payment cashWithEmail = new CashOnDeliveryPayment(emailChannel2, cashMethod);
        cashWithEmail.pay(300.0);

        System.out.println("\n=== Cash Payment with SMS Notification ===");
        Channel smsChannel2 = new SmsChannel();

        PaymentMethod cashMethod2 = new CashPayment();

        Payment cashWithSms = new CashOnDeliveryPayment(smsChannel2, cashMethod2);
        
        cashWithSms.pay(400.0);

    }
}

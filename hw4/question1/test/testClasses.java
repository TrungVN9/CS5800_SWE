import static org.junit.jupiter.api.Assertions.*;
import Notifications.Channel;
import Notifications.EmailChannel;
import Notifications.PushChannel;
import Notifications.SmsChannel;
import Payments.BitcoinPayment;
import Payments.CashOnDeliveryPayment;
import Payments.OnlinePaymentNotification;
import Payments.OnlinePayments.CreditCardPaymentMethod;
import Payments.OnlinePayments.OnlinePaymentMethod;
import Payments.OnlinePayments.PaypalPaymentMethod;
import Payments.Payment;
import org.junit.jupiter.api.Test;

public class testClasses {

    @Test
    public void testChannel() {
        // BUILD
        Channel emailChannel = new EmailChannel();
        Channel SMSChannel = new SmsChannel();
        Channel pushChannel = new PushChannel();

        // Operate
        String emailNotification = emailChannel.sendNotification();
        String SMSNotification = SMSChannel.sendNotification();
        String pushNotification = pushChannel.sendNotification();

        // Check
        assertNotNull(emailNotification);
        assertTrue(emailNotification.contains("Email Notification"));
        assertFalse(emailNotification.contains("SMS Notification"));
        assertFalse(emailNotification.contains("Push Notification"));

        assertFalse(SMSNotification.contains("Push Notification"));
        assertNotNull(SMSNotification);
        assertTrue(SMSNotification.contains("SMS Notification"));
        assertFalse(SMSNotification.contains("Email Notification"));

        assertNotNull(pushNotification);
        assertFalse(pushNotification.contains("Email Notification"));
        assertTrue(pushNotification.contains("Push Notification"));
        assertFalse(pushNotification.contains("SMS Notification"));

    }
    @Test
    public void testOnlinePayment(){
        // BUILD
        OnlinePaymentMethod creditCard =  new CreditCardPaymentMethod();
        OnlinePaymentMethod paypal = new PaypalPaymentMethod();

        // OPERATE
        String creditCardMessage = creditCard.onlinePaymentType();
        String paypalMessage = paypal.onlinePaymentType();

        //CHECK
        assertNotNull(creditCardMessage);
        assertNotNull(paypalMessage);
        assertFalse(creditCardMessage.isEmpty());
        assertFalse(paypalMessage.isEmpty());
        assertTrue(creditCardMessage.contains("CreditCard"));
        assertFalse(paypalMessage.contains("Credit Card"));
        assertTrue(paypalMessage.contains("Paypal"));
    }
    @Test
    public void testOnlinePaymentNotification() {
        //        BUILD
        Channel emailChannel = new EmailChannel();
        Channel SMSChannel = new SmsChannel();
        OnlinePaymentMethod creditCard = new CreditCardPaymentMethod();
        OnlinePaymentMethod paypal = new PaypalPaymentMethod();

        Payment payment = new OnlinePaymentNotification(emailChannel, creditCard);
        Payment payment1 = new OnlinePaymentNotification(SMSChannel, paypal);

        //        Operate
        String paymentMessage = payment.toString();
        String paymentMessage1 = payment1.toString();

        //        Check
        assertNotNull(paymentMessage);
        assertTrue(paymentMessage.contains("Email Notification"));
        assertFalse(paymentMessage.contains("SMS Notification"));
        assertFalse(paymentMessage.contains("Push Notification"));
        assertTrue(paymentMessage.contains("CreditCardPaymentMethod"));

        assertNotNull(paymentMessage1);
        assertTrue(paymentMessage1.contains("PaypalPaymentMethod"));
        assertFalse(paymentMessage1.contains("Email Notification"));
        assertTrue(paymentMessage1.contains("SMS Notification"));
        assertFalse(paymentMessage1.contains("Push Notification"));
    }

    @Test
    public void testCashOnDeliveryPayment() {
        // BUILD
        Channel emailChannel = new EmailChannel();
        Channel pushChannel = new PushChannel();

        Payment cashEmail = new CashOnDeliveryPayment(emailChannel);
        Payment cashPush = new CashOnDeliveryPayment(pushChannel);

        // OPERATE
        String cashEmailMessage = cashEmail.toString();
        String cashPushMessage = cashPush.toString();

        // CHECK
        assertNotNull(cashEmailMessage);
        assertTrue(cashEmailMessage.contains("Email Notification"));
        assertNotNull(cashPushMessage);
        assertFalse(cashPushMessage.contains("Email Notification"));
        assertTrue(cashPushMessage.contains("Push Notification"));
        assertTrue(cashPushMessage.contains("Cash On Delivery"));
        assertFalse(cashPushMessage.contains("PaypalPaymentMethod"));
        assertFalse(cashPushMessage.contains("Email Notification"));
        assertFalse(cashPushMessage.contains("BitcoinPayment"));
    }

    @Test
    public void testBitCoinPayment() {
        // BUILD
        Channel smsChannel = new SmsChannel();
        Channel pushChannel = new PushChannel();

        Payment bitcoinSMS = new BitcoinPayment(smsChannel);
        Payment bitcoinPush = new BitcoinPayment(pushChannel);

        // OPERATION
        String bitcoinPaymentSMSMessage = bitcoinSMS.toString();
        String bitcoinPaymentPushMessage = bitcoinPush.toString();

        // CHECK
        assertNotNull(bitcoinPaymentSMSMessage);
        assertNotNull(bitcoinPaymentPushMessage);
        assertFalse(bitcoinPaymentSMSMessage.contains("Email Notification"));
        assertFalse(bitcoinPaymentPushMessage.contains("SMS Notification"));
        assertFalse(bitcoinPaymentSMSMessage.contains("Push Notification"));
        assertTrue(bitcoinPaymentSMSMessage.contains("Bitcoin"));
        assertTrue(bitcoinPaymentPushMessage.contains("Bitcoin"));
    }
}
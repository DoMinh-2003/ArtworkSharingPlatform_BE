package start.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.request.RechargeRequestDTO;
import start.entity.Transaction;
import start.entity.User;
import start.entity.Wallet;
import start.enums.TransactionEnum;
import start.repository.TransactionRepository;
import start.repository.WalletRepository;
import start.utils.AccountUtils;

@Service
public class WalletService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private AccountUtils accountUtils;

    @Autowired
    private PaypalService payPalService;

    public String createPaypalPayment(RechargeRequestDTO rechargeRequestDTO) throws PayPalRESTException {
        User user = accountUtils.getCurrentUser();

        Wallet wallet = walletRepository.findWalletByUser_Id(user.getId());

        Transaction transaction = new Transaction();
        transaction.setAmount(Float.parseFloat(rechargeRequestDTO.getAmount()));
        transaction.setTransactionType(TransactionEnum.PENDING);
        transaction.setTo(wallet);
        transaction.setDescription("Recharge");
        Transaction transactionReturn = transactionRepository.save(transaction);

        Double totalAmount = Double.parseDouble(rechargeRequestDTO.getAmount());
        String currency = "USD";

        String cancelUrl = "http://yourwebsite.com/cancel";
        String successUrl = "http://mycremo.art/checkout?id="+transactionReturn.getTransactionID();

        Payment payment = payPalService.createPayment(
                totalAmount,
                currency,
                "Recharge",
                cancelUrl,
                successUrl);

        if (payment != null) {
            return payment.getLinks().get(1).getHref(); // Return the PayPal redirect URL
        } else {
            return null;
        }
    }
}

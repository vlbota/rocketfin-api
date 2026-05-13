package vlb.developer.bills.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import vlb.developer.bills.dtos.BillDTO;
import vlb.developer.bills.dtos.BillPaidDTO;
import vlb.developer.bills.enumerates.HistoryActionEnum;
import vlb.developer.bills.forms.BillCreateFORM;
import vlb.developer.bills.forms.BillPayFORM;
import vlb.developer.bills.forms.BillUpdateFORM;
import vlb.developer.bills.repositories.BillHistoryRepository;
import vlb.developer.bills.repositories.BillPaidRepository;
import vlb.developer.bills.repositories.BillRepository;
import vlb.developer.entities.*;

import java.util.UUID;

@ApplicationScoped
public class BillWriteService {

    @Inject
    BillRepository billRepository;

    @Inject
    BillPaidRepository billPaidRepository;

    @Inject
    BillHistoryRepository billHistoryRepository;

    @Inject
    BillReadService billReadService;

    @Inject
    EntityManager em;

    @Transactional
    public BillDTO create(BillCreateFORM form, UUID clientId) {
        var client = em.getReference(ClientEnty.class, clientId);
        var category = form.categoryId() != null ? em.find(CategoryBillEnty.class, form.categoryId()) : null;
        var bill = BillEnty.create(form.identifier(), form.description(), form.value(), form.due(), form.type(), client, category);
        billRepository.persist(bill);
        billHistoryRepository.persist(BillHistoryEnty.from(bill, HistoryActionEnum.CREATE));
        return BillDTO.from(bill);
    }

    @Transactional
    public BillDTO update(Long id, BillUpdateFORM form, UUID clientId) {
        var bill = billReadService.findEntityOrThrow(id, clientId);
        var category = form.categoryId() != null ? em.find(CategoryBillEnty.class, form.categoryId()) : null;
        bill.update(form.description(), form.value(), form.due(), category);
        billHistoryRepository.persist(BillHistoryEnty.from(bill, HistoryActionEnum.UPDATE));
        return BillDTO.from(bill);
    }

    @Transactional
    public void delete(Long id, UUID clientId) {
        var bill = billReadService.findEntityOrThrow(id, clientId);
        billHistoryRepository.persist(BillHistoryEnty.from(bill, HistoryActionEnum.DELETE));
        billRepository.remove(bill);
    }

    @Transactional
    public BillPaidDTO pay(Long id, BillPayFORM form, UUID clientId) {
        var bill = billReadService.findEntityOrThrow(id, clientId);
        var payMethod = form.paymentMethodId() != null ? em.find(PayMethodEnty.class, form.paymentMethodId()) : null;
        var account = form.accountId() != null ? em.find(AccountEnty.class, form.accountId()) : null;
        var billPaid = bill.pay(form.valuePaid(), form.paidAt(), payMethod, account);
        billPaidRepository.persist(billPaid);
        billHistoryRepository.persist(BillHistoryEnty.from(bill, HistoryActionEnum.PAY));
        return BillPaidDTO.from(billPaid);
    }
}

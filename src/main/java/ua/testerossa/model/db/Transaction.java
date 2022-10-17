package ua.testerossa.model.db;

import lombok.Data;
import ua.testerossa.model.Status;
import ua.testerossa.model.TransactionStatus;
import ua.testerossa.model.TransactionType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transaction_id")
  private Long transactionId;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  @Column(name = "amount")
  private Double amount;

  @Column(name = "date_time")
  private String dateTime;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "type")
  private TransactionType type;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "status")
  private TransactionStatus status;

}

package ua.testerossa.model;

public enum TransactionStatus {
  INVALID_OR_INCOMPLETE,
  CANCELLED_BY_CUSTOMER,
  AUTHORISATION_DECLINED,
  WAITING_FOR_CLIENT_PAYMENT,
  WAITING_AUTHENTICATION,
  AUTHORISED_WAITING_EXTERNAL_RESULT,
  AUTHORISATION_WAITING,
  AUTHORISATION_NOT_KNOWN,
  AUTHORISED_AND_CANCELLED,
  PAYMENT_DELETED,
  REFUND,
  PAYMENT_DECLINED_BY_THE_ACQUIRER,
  REFUND_PROCESSED_BY_MERCHANT,
  PAYMENT_REQUESTED,
  PAYMENT_PROCESSING,
  PAYMENT_UNCERTAIN,
  PAYMENT_REFUSED,
  REFUND_DECLINED_BY_ACQUIRER,
  PAYMENT_PROCESSED_BY_MERCHANT,
  BEING_PROCESSED
}

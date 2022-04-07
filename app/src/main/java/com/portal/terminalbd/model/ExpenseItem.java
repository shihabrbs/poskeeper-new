package com.portal.terminalbd.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ExpenseItem extends RealmObject {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String invoiceId;
    private int payment;
    private String transactionMethod;
    private int bankAccount;
    private int mobileBankAccount;
    private int toUser;
    private String remark;
    private int expenseCategory;
    private String created;

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getTransactionMethod() {
        return transactionMethod;
    }

    public void setTransactionMethod(String transactionMethod) {
        this.transactionMethod = transactionMethod;
    }

    public int getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(int bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getMobileBankAccount() {
        return mobileBankAccount;
    }

    public void setMobileBankAccount(int mobileBankAccount) {
        this.mobileBankAccount = mobileBankAccount;
    }

    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(int expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "ExpenseItem{" +
                "id='" + id + '\'' +
                ", invoiceId='" + invoiceId + '\'' +
                ", payment=" + payment +
                ", transactionMethod='" + transactionMethod + '\'' +
                ", bankAccount=" + bankAccount +
                ", mobileBankAccount=" + mobileBankAccount +
                ", toUser=" + toUser +
                ", remark='" + remark + '\'' +
                ", expenseCategory=" + expenseCategory +
                ", created='" + created + '\'' +
                '}';
    }
}

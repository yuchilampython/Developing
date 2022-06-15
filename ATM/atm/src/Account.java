public class Account {
    private String name;
    private String cardNumber;
    private String password;
    private Double balance = 0d;
    private Integer limit;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCardNumber(){
        return cardNumber;
    }

    public void setCardNumber(String cardNuumber){
        this.cardNumber = cardNuumber;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(Double balance){
        this.balance = balance;
    }

    public Integer getLimit(){
        return limit;
    }

    public void setLimit(Integer limit){
        this.limit = limit;
    }
    
}

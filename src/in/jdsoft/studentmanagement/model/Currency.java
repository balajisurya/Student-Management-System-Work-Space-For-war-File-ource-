package in.jdsoft.studentmanagement.model;

public class Currency {

	public String currencyTitle;
	public String currencyCode;
	public String symbolLeft;
	public String symbolRight;
	public int currencyId;
	
	
	public void setCurrencyTitle(String currencyTitle) {
		this.currencyTitle = currencyTitle;
	}
	
	public String getCurrencyTitle() {
		return currencyTitle;
	}
	
    public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
    
    public String getCurrencyCode() {
		return currencyCode;
	}
    
    public void setSymbolLeft(String symbolLeft) {
		this.symbolLeft = symbolLeft;
	}
    
    public String getSymbolLeft() {
		return symbolLeft;
	}
    
    public void setSymbolRight(String symbolRight) {
		this.symbolRight = symbolRight;
	}
    
    public String getSymbolRight() {
		return symbolRight;
	}
    
    public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}
    
    public int getCurrencyId() {
		return currencyId;
	}
	
}

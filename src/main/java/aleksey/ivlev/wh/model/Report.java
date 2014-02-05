package aleksey.ivlev.wh.model;

public class Report {

	private final Product product = new Product();
	private final Outcomes outcomes = new Outcomes();
	private final OutcomeDetails outcomeDetails = new OutcomeDetails();
	private final InStock inStock = new InStock();
	private final Incomes incomes = new Incomes();
	private final IncomeDetails incomeDetails = new IncomeDetails();
	private final DicStores dicStores = new DicStores();
	private String prodNameTmp;
	private String dicStorNameTmp;
	
	
	public String getProdNameTmp() {
		return prodNameTmp;
	}
	public String getDicStorNameTmp() {
		return dicStorNameTmp;
	}
	public Product getProduct() {
		return product;
	}
	public Outcomes getOutcomes() {
		return outcomes;
	}
	public OutcomeDetails getOutcomeDetails() {
		return outcomeDetails;
	}
	public InStock getInStock() {
		return inStock;
	}
	public Incomes getIncomes() {
		return incomes;
	}
	public IncomeDetails getIncomeDetails() {
		return incomeDetails;
	}
	public DicStores getDicStores() {
		return dicStores;
	}
	
	

}

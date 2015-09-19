import java.util.List;

public enum GeneType
	{
	GeneType("Push number into stack",100,new GeneInterface()
			{
			@Override
			public void execute(Bot bot, List<Integer> stack, int geneNumberData)
				{
				
				}
			});
	
	@SuppressWarnings("unused")
	private String info;
	private int common;
	public GeneInterface geneFunction;
	private GeneType(String info, int common, GeneInterface geneFunction)
		{
		this.info = info;
		this.common = common;
		Main.geneTotalCommon += this.common;
		this.geneFunction = geneFunction;
		}
	public int getCommon()
		{
		return common;
		}
	}

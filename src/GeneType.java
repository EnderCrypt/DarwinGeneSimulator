public enum GeneType
	{
	RAWNUMBER(true,100,(bot, stack, geneNumberData) -> // moves the raw number on the gene position into the stack
		{
		stack.push(geneNumberData);
		}),
	STORE(false,100,(bot, stack, geneNumberData) -> // uses the top stack number as memory location, and stores the next stack number there
		{
		int location = stack.pull();
		int data = stack.pull();
		bot.memory.put(location, data);
		});
	
	private static int totalCommon;
	private boolean needsRawNumber;
	private int common;
	public GeneInterface geneFunction;
	private GeneType(boolean needsRawNumber, int common, GeneInterface geneFunction)
		{
		this.needsRawNumber = needsRawNumber;
		this.common = common;
		this.geneFunction = geneFunction;
		}
	public boolean doesNeedRawNumber()
		{
		return needsRawNumber;
		}
	public int getCommon()
		{
		return common;
		}
	public static int getTotalCommon()
		{
		return totalCommon;
		}
	static
		{
		for (GeneType gt: GeneType.values())
			{
			totalCommon += gt.common;
			}
		}
	}

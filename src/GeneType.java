public enum GeneType
	{
	/*
	 * Basic genes
	 */
	STATICNUMBER(true,1000,(bot, stack, geneNumberData) -> // moves the raw number on the gene position into the stack
		{
		stack.push(geneNumberData);
		}),
	GETSTATICMEMORY(true,1000,(bot, stack, geneNumberData) -> // pushes the number at a static location onto the stack
		{
		stack.push(bot.memory.get(geneNumberData));
		}),
	STORE(false,1000,(bot, stack, geneNumberData) -> // uses the top stack number as memory location, and stores the next stack number there
		{
		int location = stack.pull();
		int data = stack.pull();
		bot.memory.put(location, data);
		}),
	/*
	 * Math genes
	 */
	INC(false,50,(bot, stack, geneNumberData) -> // increases the top number of the stack by one
		{
		int num = stack.pull();
		stack.push(num++);
		}),
	DEC(false,50,(bot, stack, geneNumberData) -> // increases the top number of the stack by one
		{
		int num = stack.pull();
		stack.push(num--);
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

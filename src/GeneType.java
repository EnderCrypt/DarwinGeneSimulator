public enum GeneType
	{
	/*
	 * Basic genes
	 */
	STATICNUMBER(true,1000,(bot, intStack, boolStack, geneNumberData) -> // moves the raw number on the gene position into the stack
		{
		intStack.push(geneNumberData);
		}),
	GETSTATICMEMORY(true,1000,(bot, intStack, boolStack, geneNumberData) -> // pushes the number at a static location onto the stack
		{
		int num = bot.memory.get(geneNumberData);
		if (num > -1)
			{
			intStack.push(num);
			}
		}),
	STORE(false,1000,(bot, intStack, boolStack, geneNumberData) -> // uses the top stack number as memory location, and stores the next stack number there
		{
		int location = Math.abs(intStack.pullInt());
		int data = intStack.pullInt();
		bot.memory.put(location, data);
		}),
	/*
	 * Math genes
	 */
	INC(false,50,(bot, intStack, boolStack, geneNumberData) -> // increases the top number of the stack by one
		{
		int num = intStack.pullInt();
		intStack.push(num++);
		}),
	DEC(false,50,(bot, intStack, boolStack, geneNumberData) -> // decreases the top number of the stack by one
		{
		int num = intStack.pullInt();
		intStack.push(num--);
		}),
	ADDITION(false,50,(bot, intStack, boolStack, geneNumberData) ->
		{
		int a = intStack.pullInt();
		int b = intStack.pullInt();
		intStack.push(a+b);
		}),
	SUBTRACTION(false,50,(bot, intStack, boolStack, geneNumberData) ->
		{
		int a = intStack.pullInt();
		int b = intStack.pullInt();
		intStack.push(a-b);
		}),
	MULTIPLY(false,50,(bot, intStack, boolStack, geneNumberData) ->
		{
		int a = intStack.pullInt();
		int b = intStack.pullInt();
		intStack.push(a*b);
		}),
	DIVIDE(false,50,(bot, intStack, boolStack, geneNumberData) ->
		{
		int a = intStack.pullInt();
		int b = intStack.pullInt();
		if (b > 0)
			{
			intStack.push(a/b);
			}
		}),
	RANDOM(false,50,(bot, intStack, boolStack, geneNumberData) ->
		{
		int num = intStack.pullInt();
		intStack.push((int)(Math.random()*(num+1)));
		}),
	MODULUS(false,50,(bot, intStack, boolStack, geneNumberData) ->
		{
		int a = intStack.pullInt();
		int b = intStack.pullInt();
		if (b > 0)
			{
			intStack.push(a%b); // needs verification
			}
		})
	;
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

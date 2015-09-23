public enum GeneType
	{
	/*
	 * Basic genes
	 */
	STATICNUMBER(true,1000,(execData) -> // moves the raw number on the gene position into the stack
		{
		execData.intStack.push(execData.gene.geneNumberData);
		}),
	GETSTATICMEMORY(true,1000,(execData) -> // pushes the number at a static location onto the stack
		{
		int num = execData.bot.memory.get(execData.gene.geneNumberData);
		if (num > -1)
			{
			execData.intStack.push(num);
			}
		}),
	STORE(false,1000,(execData) -> // uses the top stack number as memory location, and stores the next stack number there
		{
		int data = execData.intStack.pullInt();
		int location = Math.abs(execData.intStack.pullInt());
		execData.bot.memory.put(location, data);
		}),
	/*
	 * Conditional genes
	 */
	CONDITIONAL(false,1000,(execData) -> // switches between cond/start
		{
		int data = execData.intStack.pullInt();
		int location = Math.abs(execData.intStack.pullInt());
		execData.bot.memory.put(location, data);
		}),
	/*
	 * Math genes
	 */
	INC(false,50,(execData) -> // increases the top number of the stack by one
		{
		int num = execData.intStack.pullInt();
		execData.intStack.push(num++);
		}),
	DEC(false,50,(execData) -> // decreases the top number of the stack by one
		{
		int num = execData.intStack.pullInt();
		execData.intStack.push(num--);
		}),
	ADDITION(false,50,(execData) ->
		{
		int a = execData.intStack.pullInt();
		int b = execData.intStack.pullInt();
		execData.intStack.push(a+b);
		}),
	SUBTRACTION(false,50,(execData) ->
		{
		int a = execData.intStack.pullInt();
		int b = execData.intStack.pullInt();
		execData.intStack.push(a-b);
		}),
	MULTIPLY(false,50,(execData) ->
		{
		int a = execData.intStack.pullInt();
		int b = execData.intStack.pullInt();
		execData.intStack.push(a*b);
		}),
	DIVIDE(false,50,(execData) ->
		{
		int a = execData.intStack.pullInt();
		int b = execData.intStack.pullInt();
		if (b > 0)
			{
			execData.intStack.push(a/b);
			}
		}),
	RANDOM(false,50,(execData) ->
		{
		int num = execData.intStack.pullInt();
		execData.intStack.push((int)(Math.random()*(num+1)));
		}),
	MODULUS(false,50,(execData) ->
		{
		int a = execData.intStack.pullInt();
		int b = execData.intStack.pullInt();
		if (b > 0)
			{
			execData.intStack.push(a%b); // needs verification
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

public enum GeneType
	{
	/*
	 * Basic genes
	 */
	STATICNUMBER(true,1000,(execFlow) -> // moves the raw number on the gene position into the stack
		{
		execFlow.intStack.push(execFlow.gene.geneNumberData);
		}),
	GETSTATICMEMORY(true,1000,(execFlow) -> // pushes the number at a static location onto the stack
		{
		int num = execFlow.bot.memory.get(execFlow.gene.geneNumberData);
		if (num > -1)
			{
			execFlow.intStack.push(num);
			}
		}),
	STORE(false,1000,(execFlow) -> // uses the top stack number as memory location, and stores the next stack number there
		{
		int data = execFlow.intStack.pullInt();
		int location = Math.abs(execFlow.intStack.pullInt());
		execFlow.bot.memory.put(location, data);
		}),
	/*
	 * Conditional genes
	 */
	CONDITIONAL(false,250,null),// switches between cond/start
		//LAMBDA MOVED TO STATIC AT END OF FILE
	/*
	 * Math genes
	 */
	INC(false,50,(execFlow) -> // increases the top number of the stack by one
		{
		int num = execFlow.intStack.pullInt();
		execFlow.intStack.push(num++);
		}),
	DEC(false,50,(execFlow) -> // decreases the top number of the stack by one
		{
		int num = execFlow.intStack.pullInt();
		execFlow.intStack.push(num--);
		}),
	ADDITION(false,50,(execFlow) ->
		{
		int a = execFlow.intStack.pullInt();
		int b = execFlow.intStack.pullInt();
		execFlow.intStack.push(a+b);
		}),
	SUBTRACTION(false,50,(execFlow) ->
		{
		int a = execFlow.intStack.pullInt();
		int b = execFlow.intStack.pullInt();
		execFlow.intStack.push(a-b);
		}),
	MULTIPLY(false,50,(execFlow) ->
		{
		int a = execFlow.intStack.pullInt();
		int b = execFlow.intStack.pullInt();
		execFlow.intStack.push(a*b);
		}),
	DIVIDE(false,50,(execFlow) ->
		{
		int a = execFlow.intStack.pullInt();
		int b = execFlow.intStack.pullInt();
		if (b > 0)
			{
			execFlow.intStack.push(a/b);
			}
		}),
	RANDOM(false,50,(execFlow) ->
		{
		int num = execFlow.intStack.pullInt();
		execFlow.intStack.push((int)(Math.random()*(num+1)));
		}),
	MODULUS(false,50,(execFlow) ->
		{
		int a = execFlow.intStack.pullInt();
		int b = execFlow.intStack.pullInt();
		if (b > 0)
			{
			execFlow.intStack.push(a%b); // needs verification
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
		// HOTFIX CONDITIONAL
		GeneType.CONDITIONAL.geneFunction = (execFlow) -> // switches between cond/start
			{
			switch (execFlow.conditionalStatus)
				{
				case 0:
					execFlow.conditionalStatus++;
				break;
				case 1:
					boolean statmentResult = execFlow.boolStack.pullBool();
					if (statmentResult)
						{
						execFlow.conditionalStatus++;
						}
					else
						{
						while (execFlow.iterator.hasNext())
							{
							Gene gene = execFlow.iterator.next();
							if (gene.equals(GeneType.CONDITIONAL))
								{
								break;
								}
							}
						}
				break;
				case 2:
					execFlow.conditionalStatus = 0;
				break;
				default:
					System.err.println("Unknown conditionalStatus");
				}
			};
		}
	}

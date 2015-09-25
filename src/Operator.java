public enum Operator
	{
	/* 
	 * Basic genes
	 */
	STATICNUMBER(true,2000,(execFlow) -> // moves the raw number on the gene position into the stack
		{
		execFlow.intStack.push(execFlow.gene.geneNumberData);
		}),
	STATICMEMORY(true,1000,(execFlow) -> // pushes the number at a static location onto the stack
		{
		int num = execFlow.bot.memory.get(execFlow.gene.geneNumberData);
		if (num > -1)
			{
			execFlow.intStack.push(num);
			}
		}),
	STORE(false,1000,(execFlow) -> // uses the top stack number as memory location, and stores the next stack number there
		{
		int location = Math.abs(execFlow.intStack.pullInt());
		int data = execFlow.intStack.pullInt();
		//System.out.println("storing "+data+" in "+location);
		execFlow.bot.memory.put(location, data);
		}),
	/*
	 * Flow Commands:
	 */
	CONDITIONAL(false,250,null),// switches between cond/start
	/*
	 * Basic Commands:
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
		}),
	/*
	 * Logical Comparison Operators:
	 */
	GREATERTHAN(false,50,(execFlow) ->
		{
		int a = execFlow.intStack.pullInt();
		int b = execFlow.intStack.pullInt();
		execFlow.boolStack.push(a>b);
		}),
	LESSTHAN(false,50,(execFlow) ->
		{
		int a = execFlow.intStack.pullInt();
		int b = execFlow.intStack.pullInt();
		execFlow.boolStack.push(a<b);
		}),
	GREATERTHANEQUALS(false,50,(execFlow) ->
		{
		int a = execFlow.intStack.pullInt();
		int b = execFlow.intStack.pullInt();
		execFlow.boolStack.push(a>=b);
		}),
	LESSTHANEQUALS(false,50,(execFlow) ->
		{
		int a = execFlow.intStack.pullInt();
		int b = execFlow.intStack.pullInt();
		execFlow.boolStack.push(a<=b);
		}),
	EQUALS(false,50,(execFlow) ->
		{
		int a = execFlow.intStack.pullInt();
		int b = execFlow.intStack.pullInt();
		execFlow.boolStack.push(a==b);
		}),
	NOTEQUALS(false,50,(execFlow) ->
		{
		int a = execFlow.intStack.pullInt();
		int b = execFlow.intStack.pullInt();
		execFlow.boolStack.push(a!=b);
		}),
	APROXEQUALS(false,50,(execFlow) ->
		{
		int a = execFlow.intStack.pullInt();
		int b = execFlow.intStack.pullInt();
		int min = (int) (b*0.9);
		int max = (int) (b*1.1);
		execFlow.boolStack.push((a>=min) && (b<=max));
		}),
	APROXNOTEQUALS(false,50,(execFlow) ->
		{
		int a = execFlow.intStack.pullInt();
		int b = execFlow.intStack.pullInt();
		int min = (int) (b*0.9);
		int max = (int) (b*1.1);
		execFlow.boolStack.push(!((a>=min) && (b<=max)));
		}),
	/*
	 * Logic:
	 */
	TRUE(false,50,(execFlow) ->
		{
		execFlow.boolStack.push(true);
		}),
	FALSE(false,50,(execFlow) ->
		{
		execFlow.boolStack.push(false);
		}),
	AND(false,50,(execFlow) ->
		{
		boolean a = execFlow.boolStack.pullBool();
		boolean b = execFlow.boolStack.pullBool();
		execFlow.boolStack.push(a && b);
		}),
	OR(false,50,(execFlow) ->
		{
		boolean a = execFlow.boolStack.pullBool();
		boolean b = execFlow.boolStack.pullBool();
		execFlow.boolStack.push(a || b);
		}),
	XOR(false,50,(execFlow) ->
		{
		boolean a = execFlow.boolStack.pullBool();
		boolean b = execFlow.boolStack.pullBool();
		execFlow.boolStack.push(a ^ b);
		}),
	NOT(false,50,(execFlow) ->
		{
		boolean val = execFlow.boolStack.pullBool();
		execFlow.boolStack.push(!val);
		}),
	; 
	private static int totalCommon;
	private boolean needsRawNumber;
	private int common;
	public GeneInterface geneFunction;
	private Operator(boolean needsRawNumber, int common, GeneInterface geneFunction)
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
		for (Operator gt: Operator.values())
			{
			totalCommon += gt.common;
			}
		// HOTFIX CONDITIONAL
		Operator.CONDITIONAL.geneFunction = (execFlow) -> // switches between cond/start
			{
			switch (execFlow.conditionalStatus)
				{
				case 0:
					execFlow.conditionalStatus = 1;
				break;
				case 1:
					boolean statmentResult = execFlow.boolStack.pullBool();
					if (statmentResult)
						{
						execFlow.conditionalStatus = 2;
						}
					else
						{
						while (execFlow.iterator.hasNext())
							{
							Gene gene = execFlow.iterator.next();
							if (gene.operator.equals(Operator.CONDITIONAL))
								{
								break;
								}
							}
						execFlow.conditionalStatus = 0;
						}
				break;
				case 2:
					execFlow.conditionalStatus = 0;
				break;
				default:
					System.err.println("Unknown conditionalStatus");
				}
			//System.out.println(execFlow.conditionalStatus);
			};
		}
	}

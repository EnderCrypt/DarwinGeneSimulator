public class Gene
	{
	GeneType geneType;
	int geneNumberData;
	Gene (Memory memory)
		{
		int geneIndex = (int) (Math.random()*GeneType.getTotalCommon());
		for (GeneType geneT: GeneType.values())
			{
			geneIndex -= geneT.getCommon();
			if (geneIndex <= 0)
				{
				geneType = geneT;
				break;
				}
			}
		if (geneType.doesNeedRawNumber())
			{
			int nums = 1;
			while (Math.random()*(nums*2) <= 1)
				{
				nums++;
				}
			geneNumberData = (int) (Math.random()*(Math.pow(10, nums)));
			}
		}
	public void execute(Bot bot, Stack<Integer> intStack, Stack<Boolean> boolStack)
		{
		geneType.geneFunction.execute(bot, intStack, boolStack, geneNumberData);
		}
	}

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
			geneNumberData = (int) (Math.random()*memory.getMaxNumber());
			}
		}
	public void execute(Bot bot, Stack<Integer> intStack, Stack<Boolean> boolStack)
		{
		geneType.geneFunction.execute(bot, intStack, boolStack, geneNumberData);
		}
	}

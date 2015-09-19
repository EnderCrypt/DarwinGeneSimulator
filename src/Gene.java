public class Gene
	{
	GeneType geneType;
	int geneNumberData;
	Gene ()
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
			geneNumberData = (int) (Math.random()*101);
			}
		}
	public void execute(Bot bot, Stack<Integer> stack)
		{
		geneType.geneFunction.execute(bot, stack, geneNumberData);
		}
	}

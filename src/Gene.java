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
			/*
			int nums = 1;
			while (Math.random()*(nums*10) <= 5)
				{
				nums++;
				}
			geneNumberData = (int) (Math.random()*Math.pow(10, nums));//memory.getMaxNumber()
			*/
			geneNumberData = (int) (Math.random()*memory.getMaxNumber());
			}
		}
	public void execute(Bot bot, Stack<Integer> stack)
		{
		geneType.geneFunction.execute(bot, stack, geneNumberData);
		}
	}

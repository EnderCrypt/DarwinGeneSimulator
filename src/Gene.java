import java.util.List;

public class Gene
	{
	GeneType geneType;
	int geneNumberData;
	Gene ()
		{
		int geneIndex = (int) (Math.random()*Main.geneTotalCommon);
		for (GeneType geneT: GeneType.values())
			{
			geneIndex -= geneT.getCommon();
			if (geneIndex <= 0)
				{
				geneType = geneT;
				break;
				}
			}
		geneNumberData = (int) (Math.random()*101);
		}
	public void execute(Bot bot, List<Integer> stack)
		{
		geneType.geneFunction.execute(bot, stack, geneNumberData);
		}
	}

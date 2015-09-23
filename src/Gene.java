public class Gene
	{
	Operator operator;
	int geneNumberData;
	Gene (Memory memory)
		{
		int geneIndex = (int) (Math.random()*Operator.getTotalCommon());
		for (Operator geneT: Operator.values())
			{
			geneIndex -= geneT.getCommon();
			if (geneIndex <= 0)
				{
				operator = geneT;
				break;
				}
			}
		if (operator.doesNeedRawNumber())
			{
			int nums = 1;
			while (Math.random()*(nums*2) <= 1)
				{
				nums++;
				}
			geneNumberData = (int) (Math.random()*(Math.pow(10, nums)));
			}
		}
	@Override
	public String toString()
		{
		String out = operator.name();
		if (operator.doesNeedRawNumber())
			{
			out = out+"("+geneNumberData+")";
			}
		return out;
		}
	public void execute(Genome.ExecFlow execFlow)
		{
		operator.geneFunction.execute(execFlow);
		}
	}

import java.util.ArrayList;
import java.util.List;

public class Genome
	{
	public List<Gene> genes = new ArrayList<>();
	Genome()
		{
		int genesToAdd = 20 + (int) (Math.random()*20);
		for (int i=0;i<genesToAdd;i++)
			{
			genes.add(new Gene());
			}
		}
	public void execute(Bot bot)
		{
		Stack<Integer> stack = new Stack<>();
		for (Gene gene:genes)
			{
			gene.execute(bot, stack);
			}
		}
	}

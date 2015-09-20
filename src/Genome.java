import java.util.ArrayList;
import java.util.List;

public class Genome
	{
	public List<Gene> genes = new ArrayList<>();
	Genome(Memory memory)
		{
		int genesToAdd = 250 + (int) (Math.random()*250);
		for (int i=0;i<genesToAdd;i++)
			{
			genes.add(new Gene(memory));
			}
		}
	public void execute(Bot bot)
		{
		Stack<Integer> stack = new Stack<>(bot.memory.getMaxNumber());
		for (Gene gene:genes)
			{
			gene.execute(bot, stack);
			}
		System.out.println("Excess stack: "+stack.size());
		}
	}

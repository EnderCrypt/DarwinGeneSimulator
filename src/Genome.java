import java.util.ArrayList;
import java.util.Iterator;
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
		Stack<Integer> intStack = new Stack<>();
		Stack<Boolean> boolStack = new Stack<>();
		
		Iterator<Gene> iterator = genes.iterator();
		
		ExecFlow execFlow = new ExecFlow(bot, iterator, intStack, boolStack);
		
		while(iterator.hasNext())
			{
			execFlow.gene = iterator.next();
			execFlow.gene.execute(execFlow);
			}
		}
	class ExecFlow
		{
		Bot bot;
		Stack<Integer> intStack;
		Stack<Boolean> boolStack;
		Iterator<Gene> iterator;
		int conditionalStatus;
		Gene gene;
		public ExecFlow(Bot bot, Iterator<Gene> iterator, Stack<Integer> intStack, Stack<Boolean> boolStack)
			{
			this.bot = bot;
			this.iterator = iterator;
			this.intStack = intStack;
			this.boolStack = boolStack;
			this.conditionalStatus = 0;
				// 0 = outside
				// 1 = conditional
				// 2 = execute code
			}
		}
	}

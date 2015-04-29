import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
 
public class TSPNearestNeighbour
{
    private int numberOfNodes;
    private Stack<Integer> stack;
 
    public TSPNearestNeighbour()
    {
        stack = new Stack<Integer>();
    }
 
    public void tsp(int adjacencyMatrix[][],char color_array[])
    {
        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        visited[1] = 1;
        stack.push(1);
        int element, dst= 0, i;
        int color_num=1;
        char color=color_array[1];
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;
        System.out.print(1 + "\t");
 
        while (!stack.isEmpty())
        {
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numberOfNodes)
            {
                if (adjacencyMatrix[element][i] > 0 && visited[i] == 0 && (color_num<3 || (color_array[i]!=color)))
                {
                    if (min > adjacencyMatrix[element][i])
                    {
                        min = adjacencyMatrix[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag)
            {
                if(color==color_array[dst])
                    color_num+=1;
                else{
                    color_num=1;
                    color=color_array[dst];
                }
                visited[dst] = 1;
                stack.push(dst);
                System.out.print(""+dst+color_array[dst] + "\t");
                minFlag = false;
                continue;
            }
            stack.pop();
        }
    }
 
    public static void main(String... arg)
    {
        int number_of_nodes;
        Scanner scanner = null;
        try
        {
            System.out.println("Enter the number of nodes in the graph");
            scanner = new Scanner(System.in);
            number_of_nodes = scanner.nextInt();
            int adjacency_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];
            char color_array[] = new char[number_of_nodes + 1];
            System.out.println("Enter the adjacency matrix");
            for (int i = 1; i <= number_of_nodes; i++)
            {
                for (int j = 1; j <= number_of_nodes; j++)
                {
                    adjacency_matrix[i][j] = scanner.nextInt();
                }
            }
            for (int i = 1; i <= number_of_nodes; i++)
            {
                for (int j = 1; j <= number_of_nodes; j++)
                {
                    if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0)
                    {
                        adjacency_matrix[j][i] = 1;
                    }
                }
            }
            System.out.println("Enter the colors");
            String colors=scanner.next();
            for (int i = 1; i <= number_of_nodes; i++)
            {

                color_array[i] = colors.charAt(i-1);
                //scanner.next();
            }
            System.out.println("the citys are visited as follows");
            TSPNearestNeighbour tspNearestNeighbour = new TSPNearestNeighbour();
            tspNearestNeighbour.tsp(adjacency_matrix,color_array);
        } catch (InputMismatchException inputMismatch)
         {
             System.out.println("Wrong Input format");
         }
        scanner.close();
    }
}
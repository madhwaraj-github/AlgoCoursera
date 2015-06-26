import java.io.FileInputStream;


public class TestUnionFind {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// String fileName = "data/tinyUF-quick-find.txt";
		String fileName = "data/tinyUF-weighted-quick-union.txt";
		//String fileName = "data/tinyUF-weighted-quick-union-2.txt";
		System.setIn(new FileInputStream(fileName));
		// QuickFindUF.main(args);
		WeightedQuickUnionUF.main(args);;

	}

}

package pumapass.test;

import java.util.ArrayList;

import pumapass.datastorage.*;

public class VaultTest {

	public static void main(String[] args)
	{
		ArrayList<String> pList = Vault.getProfileList();
		Vault.checkManifest();
		if (pList == null)
			System.out.println("OOPS");
		else
		{
			if (pList.size() == 0)
			{
				System.out.println("empty");
			}
			else
			{
				System.out.println("has some");
			}
		}
	}

}

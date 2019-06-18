/*
1)long(-ls)
2)printAll (-a)
3)sortByFileSize (-S)
4)sortByTime&Date (-t)
5)list in reverseOrder (-r)
*/
package BonusHW;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class ListDirectoy {
	
	String path;
	boolean verbose;
	boolean help;
	boolean fullpath;

	public static void main(String[] args) {
		ListDirectoy myRunner = new ListDirectoy();
		myRunner.run(args);
	}

	private void run(String[] args) {
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
			// path is required (necessary) data so no need to have a branch.
			System.out.println("You provided \"" + path + "\" as the value of the option p");
			
			// TODO show the number of files in the path
			/*int count = new File("C:\\").list().length;
			System.out.println("Number of file: " + count);*/
			
			File file = new File(path);
			System.out.println(file.listFiles().length);
			
			if(verbose) {
				// TODO list all files in the path
				File folder = new File(path);
				listFilesForFolder(folder);
				System.out.println("Your program is terminated. (This message is shown because you turned on -v option!");

			}

			if(fullpath) {
				File file1 = new File(path);
				System.out.println("Fullpath: " + file1.getAbsolutePath());
			}
			
		}
	}
	
	
	public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	        }
	    }
	}	
	
	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&//
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			path = cmd.getOptionValue("p");
			verbose = cmd.hasOption("v");
			help = cmd.hasOption("h");
			fullpath = cmd.hasOption("f");
			
			//System.out.println(cmd.getOptionValue("a"));
			//System.out.println(cmd.getOptionValue("b"));

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();
		
		//options.addOption("a", "abc", true, "First parameter");
		//options.addOption("b", "bcd", true, "Second parameter");
		

		// add options by using OptionBuilder
		options.addOption(Option.builder("p").longOpt("path")
				.desc("Set a path of a directory or a file to display")
				.hasArg()
				.argName("Path name to display")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("v").longOpt("verbose")
				.desc("Display detailed messages!")
				//.hasArg()     // this option is intended not to have an option value but just an option
				.argName("verbose option")
				//.required() // this is an optional option. So disabled required().
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
		        .build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("f").longOpt("fullpath")
				.desc("Set a fullpath of a directory or a file to display")
				.build());

		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI test program";
		String footer ="\nPlease report issues at https://github.com/lifove/CLIExample/issues";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}

}

package org.corgiking.genclass;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Main {
	JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	StandardJavaFileManager stdJFileManager = compiler.getStandardFileManager(null, null, null);
}

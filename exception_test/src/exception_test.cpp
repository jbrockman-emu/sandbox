//============================================================================
// Name        : exception_test.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

void throw20()
{
	throw 20;
}

int test1() {
  try
  {
    throw20();
    cout << "line after throw" << endl;
  }
  catch (int e)
  {
    cout << "An exception occurred. Exception Nr. " << e << endl;
    cout << "line after catch" << endl;
    exit(e);
  }
  cout << "line before return" << endl;
  return 0;
}

void test2()
{
	cout << "inside test2" << endl;
	throw 1;
	cout << "line after throw in test2" << endl;
}

int main()
{
	test2();

	cout << "end of program" << endl;
	return 0;
}

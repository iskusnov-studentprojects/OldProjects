#include <gl/glut.h>
#include <gl/GL.h>
#include <gl/glu.h>
#include <stdio.h>
#include <math.h>
#include "Point.h"
#include "MandelbrotSet.h"
#include "IFS_2D.h"
#include "IF_2D.h"

int limit = 600;
int widthPic = limit;
int heightPic = limit;
double beginsetx = 0.3656;
double beginsety = 0.3561;
double endsetx = 0.385;
double endsety = 0.3745;
double step = (endsetx-beginsetx)/limit;
unsigned int variable = 0;
Point* mset;
Point2D* pointset;
int iterations = 100000;

void displayMandelbrotSet() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glMatrixMode(GL_MODELVIEW);

	glLoadIdentity();

	glBegin(GL_POINTS);
	for (int i = 0; i < widthPic*heightPic; i++) {
		Color color = calculateColor(mset[i].getCharacteristic(), variable);
		glColor3d(color.red, color.green, color.blue);
		glVertex2d(mset[i].getX(), mset[i].getY());
	}
	glEnd();

	glutSwapBuffers();
}

void displayIFS() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glMatrixMode(GL_MODELVIEW);

	glLoadIdentity();

	glBegin(GL_POINTS);
	for (int i = 0; i < iterations; i++) {
		glColor3d(0, 0, 1);
		glVertex2d(pointset[i].getX(), pointset[i].getY());
	}
	glEnd();

	glutSwapBuffers();
}

void reshape(int w, int h) {
	glMatrixMode(GL_PROJECTION);

	glLoadIdentity();

	glOrtho(beginsetx, endsetx, beginsety, endsety, 0, 2);

	glViewport(0, 0, w, h);
}

void initializeMandelbrotSet() {
	mset = makeMassivePoints(beginsetx, beginsety, step, widthPic, heightPic, limit);

	glEnable(GL_DEPTH_TEST);
	glShadeModel(GL_SMOOTH);
	glEnable(GL_CULL_FACE);
}

void initializeIFS() {
	/*
	pointset = start(IF_2D(0.3, -0.3, 1, 0.3, 0.3, 1, 0.25),
		IF_2D(0.3, -0.3, 1, 0.3, 0.3, -1, 0.25),
		IF_2D(0.3, -0.3, -1, 0.3, 0.3, 1, 0.25),
		IF_2D(0.3, -0.3, -1, 0.3, 0.3, -1, 0.25),
		Point2D(100, 100),
		iterations);
	*/
	//*
	pointset = start(IF_2D(0.333, 0, 13.333, 0, 0.333, 200, 0.25),
		IF_2D(0.333, 0, 413.333, 0, 0.333, 200, 0.25),
		IF_2D(0.167, 0.289, 130, -0.289, 0.167, 256, 0.25),
		IF_2D(0.167, -0.289, 403, 0.289, 0.167, 71, 0.25),
		Point2D(100, 100),
		iterations);
	//*/
	glClearColor(0.0, 0.0, 0.0, 1.0);

	beginsetx = findMinX(pointset, iterations);
	beginsety = findMinY(pointset, iterations);
	endsetx = findMaxX(pointset, iterations);
	endsety = findMaxY(pointset, iterations);

	glEnable(GL_DEPTH_TEST);
	glShadeModel(GL_SMOOTH);
	glEnable(GL_CULL_FACE);
}

void timer(int value) {
	variable++;
	glutPostRedisplay();
	glutTimerFunc(20, timer, 1);
}

int main(int argc, char * argv[])
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA);

	glutInitWindowSize(600, 600);
	glutCreateWindow("Курсовая работа");

	initializeIFS();
	glutDisplayFunc(displayIFS);
	glutReshapeFunc(reshape);
	glutTimerFunc(33, timer, 1);

	glutMainLoop();
	delete pointset;
	return 0;
}

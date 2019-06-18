#include <gl/glut.h>
#include <gl/GL.h>
#include <gl/glu.h>
#include <gl/GLAux.h>
#include <stdio.h>
#include <math.h>

using namespace std;

float PI = 3.14159265;
float angleA = 30;
float angleB = 30;
int eyeX = 0;
int eyeY = 0;
int eyeZ = 0;

GLUquadricObj * quadric;
unsigned int textures[1];

float ambient[] = { 0.1, 0.1, 0.1, 1 };
float diffuse[] = { 0.4, 0.4, 0.4, 1 };
float specular[] = { 0.4, 0.4, 0.4, 1 };
float blick[] = { 1, 1, 1, 1 };
float lpos[] = { 0, 150, 0, 1 };
float cubeMaterial1[] = { 0.1,0.5,0.3,1 };
float cubeMaterial2[] = { 0.3,0.7,0.5,1 };
float cubeMaterial3[] = { 0.3,0.3,0.3,1 };
float sphereMaterial1[] = { 1,1,1,1 };
float sphereMaterial2[] = { 1,1,1,1 };
float sphereMaterial3[] = { 1,1,1,1 };
float bublicMaterial1[] = { 0.4,0.1,0.7,1 };
float bublicMaterial2[] = { 0.3,0.4,0.6,1 };
float bublicMaterial3[] = { 0.8, 0.8, 0.8, 1 };

void reshape(int w, int h)
{
	glMatrixMode(GL_PROJECTION);

	glLoadIdentity();

	glOrtho(-600, 600, -600, 600, -600, 600);

	glViewport(0, 0, w, h);
}

void timer(int value) {
	if (angleA < 360)
		angleA += 1;
	else
		angleA = 1;
	if (angleB < 360)
		angleB += 1;
	else
		angleB = 1;
	//eyeX = 200*cos(angleA*PI / 180)*cos(angleB*PI / 180);
	//eyeZ = 200 * cos(angleA*PI / 180)*sin(angleB*PI / 180);
	//eyeY = 200 * sin(angleB*PI / 180);
	eyeX = 200 * cos(angleA*PI / 180);
	eyeZ = 200 * sin(angleA*PI / 180);
	eyeY = 50;
	glutPostRedisplay();
	glutTimerFunc(30, timer, 1);
}

void display()
{	
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glMatrixMode(GL_MODELVIEW);

	glLoadIdentity();

	gluLookAt(eyeX, eyeY, eyeZ, 0, 0, 0, 0, 1, 0);

	glLightModelfv(GL_LIGHT_MODEL_AMBIENT, ambient);
	glLightfv(GL_LIGHT0, GL_AMBIENT, ambient);
	glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse);
	glLightfv(GL_LIGHT0, GL_SPECULAR, specular);
	glLightfv(GL_LIGHT0, GL_POSITION, lpos);
	glNormal3f(0, 0, 1);

	glEnable(GL_TEXTURE_2D);
	//Сфера
	glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, sphereMaterial1);
	glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, sphereMaterial2);
	glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, sphereMaterial3);
	glMateriali(GL_FRONT_AND_BACK, GL_SPECULAR, 128);

	glBindTexture(GL_TEXTURE_2D, textures[0]);
	glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_SPHERE_MAP);
	glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_SPHERE_MAP);
	glPushMatrix();       
	glTranslated(-150, 40, -150);  
	glColor3d(0, 1, 0);
	gluSphere(quadric, 50, 50, 50);
	glPopMatrix();  

	glDisable(GL_TEXTURE_2D);
	//Куб
	glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, cubeMaterial1);
	glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, cubeMaterial2);
	glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, cubeMaterial3);
	glMateriali(GL_FRONT_AND_BACK, GL_SPECULAR, 64);
	glPushMatrix();       
	glTranslated(100, 40, 100);  
	glColor3d(1, 0, 1);
	glutSolidCube(100);
	glPopMatrix();
	
	//Бублик
	glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, bublicMaterial1);
	glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, bublicMaterial2);
	glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, bublicMaterial3);
	glMateriali(GL_FRONT_AND_BACK, GL_SPECULAR, 10);
	glPushMatrix();
	glTranslated(-250, -10, -240);
	glColor3d(1, 0, 0);
	glutSolidTorus(40, 50, 50, 60);
	glPopMatrix();  
	glutSwapBuffers();
}

void loadTexture() {
	AUX_RGBImageRec *texture1 = auxDIBImageLoadA("cube.dib");

	glGenTextures(1, textures);
	glBindTexture(GL_TEXTURE_2D, textures[0]);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	glTexImage2D(GL_TEXTURE_2D, 0, 3, texture1->sizeX, texture1->sizeY, 0, GL_RGB, GL_UNSIGNED_BYTE, texture1->data);
}

void initialize() {
	glClearColor(0.0, 0.0, 0.0, 1.0);

	loadTexture();
	glEnable(GL_DEPTH_TEST);
	glShadeModel(GL_SMOOTH);
	glEnable(GL_CULL_FACE);
	glEnable(GL_LIGHTING);
	glEnable(GL_COLOR_MATERIAL);
	glEnable(GL_LIGHT0);
	glEnable(GL_TEXTURE_2D);

	quadric = gluNewQuadric();
	gluQuadricDrawStyle(quadric, GLU_FILL);
	gluQuadricTexture(quadric, GL_TRUE);
	gluQuadricNormals(quadric, GLU_SMOOTH);
	
	glEnableClientState(GL_VERTEX_ARRAY);
	glEnableClientState(GL_COLOR_ARRAY);
}

int main(int argc, char * argv[])
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA);

	glutInitWindowSize(800, 800);
	glutCreateWindow("Трассировка лучей");

	initialize();
	glutReshapeFunc(reshape);
	glutDisplayFunc(display);
	glutTimerFunc(33, timer, 1);

	glutMainLoop();

	gluDeleteQuadric(quadric);
	return 0;
}
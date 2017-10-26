# -*- coding: utf-8 -*-

# COMPLETAR PARA LA ENTREGA DE ESTA PRÁCTICA:
# Fecha:
# Alumno: Josep Dols

from PIL import Image, ImageTk
import tkinter
import random
import numpy
import sys
import time
import math
import operator

def compute_gradient(grad,img):
    """
    img  is a 2-dimensional grayscale image in a list of list format
    grad is the output represented in the same way
    path is None during the first iteration and contains the previous seam path afterwards
    observe that the gradient is not computed for the first and last rows 
    so that you do not have to use these first and last rows
    """
    width, height = len(grad[0]), len(grad)

    # do not modify this function in this template

    # first and last rows compute a different, simpler, gradient
    for y in (0, height-1): # just first and last rows
        for x in range(1, width-1): # first and last columns are excluded
            grad[y][x] = abs(img[y][x-1] - img[y][x+1])

    for y in range(1,height-1): # gradient for the rest of rows is based on Sobel operator
        for x in range(1, width-1): # first and last columns are excluded
            gx = -img[y-1][x-1]-2*img[y][x-1]-img[y+1][x-1]+img[y-1][x+1]+2*img[y][x+1]+img[y+1][x+1]
            gy =  img[y-1][x-1]+2*img[y-1][x]+img[y-1][x+1]-img[y+1][x-1]-2*img[y+1][x]-img[y+1][x+1]
            grad[y][x] = math.sqrt(gx*gx+gy*gy)

def paint_seams(height,seam_paths,color_matrix,path_color=[0,0,0]):
    """
    You don't need to modify this function
    """
    #print(height)
    for y in range(height):
        for path in seam_paths:
            color_matrix[y][path[y]] = path_color

def remove_seams(height,seam_paths, matrix):
    """
    COMPLETE
    """
    ##print("Llego aqui: remove seams")
    for y in range(height):
        for path in seam_paths:
#            if len(matrix[y]) <= path[y]:
#                path[y] = len(matrix[y]) -1
            matrix[y].pop(path[y])

    #pass

def dp_seam_carving_multi(grad,mat,N,pscore=0.8):
    """
    dynamic programming version which finds many paths/seams and
    returns them as a list of paths

    N is the maximum number of seams to be returned, the actual number
    of returned paths might be lower if they are not found.

    A path is discarded if it has a pixel in common with a previous path
    or if its total score multiplied by pscore is not <= best_score
    """
    width, height = len(grad[0]), len(grad)
    infty=1e99
    columnas_restantes = removed_colums
    # first row deserves special treatment:
    mat[0][0]       = infty
    mat[0][width-1] = infty
    for x in range(1,width-1):
        mat[0][x] = grad[0][x]
    # the rest of rows
    for y in range(1,height):
        mat[y][0]       = infty
        mat[y][width-1] = infty
        # COMPLETE
        for i in range(1,width-1):
            mat[y][i] = grad[y][i] + min(mat[y-1][i-1],mat[y-1][i],mat[y-1][i+1])

    paths = []

    puntosInsertados = []
    for i in range(height-2,-1,-1):
        puntosInsertados.append([])

    list_v =sorted ([(mat[-1][i], i) for i in range(width)])
    list_v = list_v[:N]

    auxList = sorted((y,x) for (x,y) in enumerate(mat[-1]))
    bestN = auxList[:N]

    pesoMin = 0
    for punto in list_v:
        if punto[1] >= width:
            break
        min_point = punto[1]
        peso = 0
        heSalido = 0
        path = [min_point]
        for i in range(height-2,-1,-1):
            values = (mat[i-1][min_point-1],mat[i-1][min_point],mat[i-1][min_point+1])
            min_point2, min_val = min(enumerate(values), key=operator.itemgetter(1))
            peso = peso + min_val

            if(min_point2==0):
                min_point = min_point - 1
            elif(min_point2==2):
                 min_point = min_point +1

            if(min_point in puntosInsertados[i]):
                heSalido = 1
                break
            else:
                puntosInsertados[i].append(min_point)
                path.append(min_point)
        if(len(paths)==0):
            pesoMin = peso
        if((heSalido==0) & ((peso/pesoMin)>pscore)):
            path.reverse()
            paths.append(path)

#        auxSet = set()
#        auxList = []
#        if(mat[-1][auxList[i][1]]*pscore<=top_value):
#            listaIn = path_from_min_point(mat,auxList[i][1])
#            for j in range(height-1,-1,-1):
#                if (listaIn[j] in puntosInsertados[j]):
#                    break
#            if (j==0):
#                paths.append(listaIn)
#                for k in range(len(puntosInsertados)):
#                    puntosInsertados[k].append(listaIn[-k])
#                #pathSet = pathSet.union(auxSet)
#        else:
#            break
    paths = sorted(paths, key=lambda x: sum(x))
    paths.reverse()
    return paths

def matrix_to_color_image(color_matrix):
    """
    You don't need to modify this function
    """
    return Image.fromarray(numpy.array(color_matrix, dtype=numpy.uint8))
    
def save_matrix_as_color_image(color_matrix,filename):
    """
    You don't need to modify this function
    """
    img = matrix_to_color_image(color_matrix)
    img.save(filename)

######################################################################
#################       GRAPHICAL APPLICATION       ##################
######################################################################

class MyTkApp():
    """
    You don't need to modify this class
    """
    def __init__(self,
               color_img,
               removed_colums,
               max_number_seams):

        self.root=tkinter.Tk()
        self.root.title("Seam Carving")
        self.color_img = color_img
        self.removed_colums = removed_colums
        self.max_number_seams = max_number_seams
        width, height = color_img.size
        print("Width: ", width,", Height: ",height)
        height = min(720, height)
        self.root.geometry('%dx%d' % (width, height+64))
        self.canvas = tkinter.Canvas(self.root.master,width=width,height=height)
        # Image
        imTk = ImageTk.PhotoImage(color_img)
        self.center_x = imTk.width()/2
        self.center_y = imTk.height()/2
        self.canvas_img = imTk
        self.canvas.pack()

        l = tkinter.Label(self.root)
        l.pack()
        self.b = tkinter.Button(self.root, text="Begin", command=self.runSeamCarving)
        self.b.pack()
        self.running = True
        self.root.mainloop()

    def showImg(self, im):
        "Updating image"
        imTk = ImageTk.PhotoImage(im)
        width, height = im.size
        self.canvas.delete(self.canvas_img)
        self.canvas_img = imTk
        self.canvas.create_image(self.center_x, self.center_y, image = self.canvas_img)
        self.canvas.update()

    def runSeamCarving(self):
      self.b.config(text="Carving...")
      t0 = time.time()

      color_img = self.color_img
      removed_colums = self.removed_colums
      width,height = color_img.size
      # convert the color image to a numpy array    
      color_numpy = numpy.array(color_img.getdata()).reshape(height, width,3) # 3 for RGB
      # convert the numpy array into a list of lists, we will use this
      # list of lists (a list of rows) as our data structure during the
      # computations:
      color_matrix = color_numpy.tolist()
      
      # make the same for the grayscale version of the image:
      grayscale_img = color_img.convert("F")
      grayscale_numpy = numpy.array(grayscale_img.getdata()).reshape(height,width)
      grayscale_matrix = grayscale_numpy.tolist()
      
      # let's construct the gradient matrix as a list of lists:
      gradient_matrix = [[0.0 for x in range(width)] for y in range(height)]
      # let's construct the dynamic programming matrix as a list of lists:
      infty = 1e99
      dp_matrix = [[infty for x in range(width)] for y in range(height)]
      
      self.showImg(color_img) # show image
      while self.removed_colums>0:
        # compute the gradient
        compute_gradient(gradient_matrix,grayscale_matrix)
        # call the DP algorithm:
        N = min(self.removed_colums,self.max_number_seams)
        seam_paths = dp_seam_carving_multi(gradient_matrix,dp_matrix,N)
        paint_seams(height,seam_paths,color_matrix)
        # paint and show the seam
        self.showImg(matrix_to_color_image(color_matrix))
        # remove the seam path from the color matrix:
        remove_seams(height,seam_paths,color_matrix)
        # remove from the grayscale_matrix
        remove_seams(height,seam_paths,grayscale_matrix)
        # remove from the gradient matrix
        remove_seams(height,seam_paths,gradient_matrix)
        # decrement width
        width -= len(seam_paths)
        self.removed_colums -= len(seam_paths)
        # paint and show the seam
        self.showImg(matrix_to_color_image(color_matrix))

      # finally, save the resulting image:
      output_file = "seam_carved_" + file_name
      save_matrix_as_color_image(color_matrix,output_file)
      t = time.time() - t0
      print('Final time:', t)
      self.b.config(text="Begin")

######################################################################
######################       MAIN PROGRAM       ######################
######################################################################
if __name__ == "__main__":
    """
    You don't need to modify the main function
    """
    if len(sys.argv) != 4:
        print('\n%s image_file {num_column|%%} number_seams\n'\
              % (sys.argv[0],))
        sys.exit()
        
    file_name = sys.argv[1]
    ncolumns = sys.argv[2]
    N = int(sys.argv[3])

    # open image
    color_img = Image.open(file_name)
    width,height = color_img.size

    # it is required to open image before processing this parameter in
    # case columns are relative
    if ncolumns[-1] == '%':
        ncolumns = int(float(ncolumns[:-1]) * width / 100)
    else:
        ncolumns = int(ncolumns)
    # python allows us to write 3<ncolumns<width
    # but most other programming languages dont
    assert 3 < ncolumns and ncolumns < width
    # number of columns to be removed
    removed_colums = width - ncolumns

    # tkinter
    app = MyTkApp(color_img,
                  removed_colums,
                  N)


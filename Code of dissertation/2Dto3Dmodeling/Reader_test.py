'''
Created on 2018年1月23日

@author: Lemon
'''
'''
Created on 2017年12月12日

@author: Lemon
'''
import numpy as np
from stl import mesh
import cv2

img_dir=r'C:\Users\Lemon\Desktop\13.jpg'
out_dir='001.stl'
print(img_dir)




def convert_image(img1, out_dir, invert):
    
   
    print('Start Convert')
    vertices_array = []
    faces_array = []
    print('invert=',invert)
    for x,row in enumerate(img1):
        for y,pixel in enumerate(row):
            if invert:
                vertices = np.array([\
                    [x, y, 0],
                    [x+1, y, 0],
                    [x+1, y+1, 0],
                    [x, y+1, 0],                

                    [x, y, 255-pixel],
                    [x+1, y, 255-pixel],
                    [x+1, y+1, 255-pixel], 
                    [x, y+1, 255-pixel],
                ])

            else:
                vertices = np.array([\
                    [x, y, 0],
                    [x+1, y, 0],
                    [x+1, y+1, 0],
                    [x, y+1, 0],                

                    [x, y, pixel],
                    [x+1, y, pixel],
                    [x+1, y+1, pixel], 
                    [x, y+1, pixel],
                ])

            vertices_array.append(vertices)

    
    faces = np.array([[0,3,1],[1,3,2],[0,4,7],[0,7,3],[4,5,6],[4,6,7],[5,1,2],[5,2,6],[2,3,6],[3,7,6],[0,1,5],[0,5,4]])
    b = np.array([0.3,0.3,0.03])
    #faces=faces*b
    #print('faces=')
    vertices_array=vertices_array*b
    #print('vertices=')
    #print(vertices)
    #print('vertices_array=')
    #print(vertices_array)
    #print('Combining...')
    #creates meshes from vertices
    meshes = [] 
    for vertice in vertices_array:
        cube = mesh.Mesh(np.zeros(faces.shape[0], dtype=mesh.Mesh.dtype))
        for i, f in enumerate(faces):
            for j in range(3):  
                cube.vectors[i][j] = vertice[f[j],:]
        
        meshes.append(cube)
    #print(faces)
    #print('cube=')
    #print(cube)
    #print('mesh=')
    #print(mesh)
    
    
    #combines all meshes together
    print('Length data')
    total_length_data = 0
    for i in range(len(meshes)):
        total_length_data += len(meshes[i].data)

    print('2')
    data = np.zeros(total_length_data, dtype = mesh.Mesh.dtype)
    
    data['vectors'] = np.array(meshes).reshape((-1, 9)).reshape((-1, 3, 3))
    mesh_final = mesh.Mesh(data.copy())
    #saves final mesh
    print('1')
    
    mesh_final.save(out_dir)
    print('File Saved')



def get_gray_img(img_dir1):
    
    #img_dir1=imageadjust(img_dir)
    cv2.imshow('image',img_dir1)
    cv2.waitKey(0)
    print('12')
    global gray_img
    #print(img_dir)
    #img = cv2.imread(img_dir)
    #print(img_dir)
    gray_img = cv2.cvtColor(img_dir1, cv2.COLOR_RGB2GRAY)   
    #gray_img = img_dir1
    cv2.imshow("Gray", gray_img)
    cv2.waitKey (0)
    return gray_img
    print('123')

def blur_img(gray_img):
    blur=(1,1)
    print('123456')
    global img1
    img1 = cv2.blur(gray_img,blur)
    #img1 = cv2.GaussianBlur(gray_img,(1,1),100)
    cv2.imshow("blur1",img1)
    cv2.waitKey (0)
    return img1
    print('1234567')

def run(img_dir1, out_dir, invert):
    
    
    
    print(img_dir)
    get_gray_img(img_dir1)
    print('invert=')
    print(invert)
    img1 = blur_img(gray_img)
    
    print('Blur done')
    convert_image(img1, out_dir, invert)
    
    

def imageadjust(img_dir):
    global img_dir1
    imgbig=cv2.imread(img_dir)
    cv2.imshow('image',imgbig)
    cv2.waitKey(0)
    sp=imgbig.shape 
    hight=sp[0]
    width=sp[1]
    print('hight = ',hight)
    print('width = ',width)
    if hight<250 and width<250:
        img_dir1=imgbig
        cv2.imshow('image',img_dir1)
        cv2.waitKey(0)
        return img_dir1
    elif hight>251 and width>251:
        img_dir1=cv2.resize(imgbig,(int(width/3.5),int(hight/3.5)),0,0, interpolation=cv2.INTER_CUBIC)
        print(img_dir1.shape)
        #cv2.imshow('image',img_dir1)
        #cv2.waitKey(0)
        return img_dir1
    else :
        print("out of proportion")
        img_dir1=imgbig
        return img_dir1

imageadjust(img_dir)
cv2.imshow('image',img_dir1)
cv2.waitKey(0)


run(img_dir1, out_dir,1)

 
print('1')

print('End')

from bs4 import BeautifulSoup
import urllib2
import re
import sys

arq = open("liks.txt","w")

L=[]
N=[]

def getAll(link):
	html_page = urllib2.urlopen(link)
	soup = BeautifulSoup(html_page)
	i=0
	for link in soup.findAll('a'):
	    if (i>7) and (i%2==0) :
	        L.append("https://matriculaweb.unb.br/matriculaweb/graduacao/"+link.get('href'))
	        N.append(link.string)
            i=i+1

def getInd(link):
    html_page = urllib2.urlopen(link)
    soup = BeautifulSoup(html_page)
    for bi in soup.findAll('font'):
        if bi.get('size')=='4':
            print(bi.string)

getAll("https://matriculaweb.unb.br/matriculaweb/graduacao/oferta_dis.aspx?cod=650")
i=0
for link in L:
    arq.write( link+'\n' )
arq.close
#isso demora
for link in L:
    print N[i]
    print "Turmas"
    i=i+1
    getInd(link)
#print L
#print len(L)

importdata("./data/spam/tr.dat",' ',4);
tr = ans.data;
importdata("./data/spam/trlabels.dat",' ',4);
trlabels = ans.data;
importdata("./data/spam/ts.dat",' ',4);
ts = ans.data;
importdata("./data/spam/tslabels.dat",' ',4);
tslabels = ans.data;

L = size(ts,1);
%C-> de 10 a 10000
%t-> de 0 a 4
t = 1;
C = 1000;
for i = 1:50:2000
    Cl = i;
    input = sprintf('-t %d -c %d -q',t,Cl);
    res=svmtrain(trlabels,tr,input);
    [a,acc,b]=svmpredict(tslabels,ts, res,'-q');
    acuracy = (100-acc(1))/100;
    Ic = 1.96*sqrt((acuracy*(1-acuracy))/L);
    fprintf('%d\t%d\t%f\t%f\t%f\n',t,Cl,acuracy,acuracy-Ic,acuracy+Ic);
end
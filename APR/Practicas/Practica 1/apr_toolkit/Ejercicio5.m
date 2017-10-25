importdata("./data/usps/tr.dat",' ',4);
tr = ans.data;
importdata("./data/usps/trlabels.dat",' ',4);
trlabels = ans.data;
importdata("./data/usps/ts.dat",' ',4);
ts = ans.data;
importdata("./data/usps/tslabels.dat",' ',4);
tslabels = ans.data;

L = size(ts,1);
%C-> de 10 a 10000
%t-> de 0 a 4
t = 1;
C = 1000;
axis([0 1000 0 0.35])
set(gca,'xscale','log');
hold on
for j= 0:1:3
    t = j;
    resMat = [];
    numbers = round(logspace(0,3,10));
    parfor i = 1:10
        Cl = numbers(i);
        input = sprintf('-t %d -c %d -q',t,Cl);
        res=svmtrain(trlabels,tr,input);
        [a,acc,b]=svmpredict(tslabels,ts, res,'-q');
        acuracy = (100-acc(1))/100;
        Ic = 1.96*sqrt((acuracy*(1-acuracy))/L);
        resMat = [resMat [Cl acuracy Ic]'];
        fprintf('%d\t%d\t%f\t%f\t%f\n',t,Cl,acuracy,acuracy-Ic,acuracy+Ic);
    end
    resMat = resMat';
    errorbar(resMat(:,1),resMat(:,2),resMat(:,3));
end
hold off
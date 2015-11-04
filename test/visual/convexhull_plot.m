function r = convexhull_plot(fin,fhull)

set  = importdata(fin);
set  = set(2:numel(set));
x    = set(1:2:numel(set));
y    = set(2:2:numel(set));
scatter(x,y,'s');
hold on;

hull = importdata(fhull);
hull = [ hull; [hull(1,1),hull(1,2)] ] %Connect the last point to the last

plot(hull(:,1),hull(:,2),'r','linewidth',2);





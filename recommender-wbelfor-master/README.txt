Code Description: 


TreeGenerator takes in the ListObjsData and uses the node classes to build the tree.
ListObjsData takes in a Linked List of IAttibuteDatums and provides you with a list of methods that are helpful in handling the data. Take in ListObjsData class and holds a tree of node classes to make predictions. 




Socially Responsible Computing:




Readings on train_candidates_unequal.csv:
---------------------------------------------------
Cis Female ratio hired 0.0
Cis Male ratio hired 0.36


Cis Female ratio hired 0.0
Cis Male ratio hired 0.43


Cis Female ratio hired 0.18
Cis Male ratio hired 0.34


Cis Female ratio hired 0.06
Cis Male ratio hired 0.2


Cis Female ratio hired 0.26
Cis Male ratio hired 0.74






Readings for train_candidates_equal.csv:
-------------------------------------------------


Cis Female ratio hired 0.01
Cis Male ratio hired 0.01


Cis Female ratio hired 0.04
Cis Male ratio hired 0.07


Cis Female ratio hired 0.05
Cis Male ratio hired 0.18


Cis Female ratio hired 0.01
Cis Male ratio hired 0.01


Cis Female ratio hired 0.06
Cis Male ratio hired 0.08








Question 1: The articles talks about how much of the bias in “the hiring process” goes on far before the hiring process when the algorithms for showing the job openings are set to show the ad to people who are the most likely to click the ad rather than those who are well-equipped to excel at it. This heavily skews the job applicant pool by not aligning with the company’s values. Furthermore, studies have shown that many of these algorithms will target only a certain demographic for specific jobs whereas a company may be looking to diversify its work environment. 


Question 2: I notice that the hiring rate for cis females is far lower than that of men. What I found interesting was that the cis female ratio would come out to 0.0 very often which suggested the same notion that Miranda Bogen mentions in her article where she writes that studies show that when choosing a demographic, the algorithms seem to put all their eggs in one basket. This was something that I observed when running BiasTest parsing train_candidates_unequal.csv. 


Question 3: There is still some bias when running BiasTest parsing train_candidates_equal.csv. However, the bias seemed to be reduced. When running this file in testing, I failed to achieve an even ratio, but my recommender produced equal ratios twice out of five attempts which suggests that the dataset was fairer. 


Question 4: The implementation of randomly selecting the order of the attributes is what caused major variation in the ratios for each time running the file. The order of attributes heavily impacts the consistency and precision of the results.




Question 5: The hiring rates change each time we build a new classifier because of the random nature of the order of attributes. If the order of attributes is chosen carefully, there is a chance for the bias to be eliminated to an extent, but I don’t think it will be ever fully eliminated.


Question 6: Yes. There is still a hiring bias when ran with the train_candidates_correlated.csv because, as the prompt suggests, the biases go beyond just the gender attribute as many of the other attributes are also gender-biased themselves. 


Question 7: A few limitations of the current implementation of BiasTest are that, for one, the order of attributes considered changes every time the program is run. Secondly, if the goal was diversity and inclusion, there could be more attributes focused around such qualities in applicants. There could be humans who assess applicants who don’t like certain attributes based on their own personal beliefs which can get in the way of the hiring process in a way that BiasTest wouldn’t.
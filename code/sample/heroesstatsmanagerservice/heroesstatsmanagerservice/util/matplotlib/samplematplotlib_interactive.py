import pandas as pd
import numpy as np

import cufflinks as cf
import plotly.offline as plyo

plyo.init_notebook_mode(connected=True)

a = np.random.standard_normal((250, 5)).cumsum(axis=0)

index = pd.date_range('2019-1-1', freq='B', periods=len(a))

df = pd.DataFrame(100 + 5 * a, columns=list('abcde'), index=index)

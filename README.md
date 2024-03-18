# [dataDM](https://github.com/approximatelabs/datadm) 💬📊

[![PyPI](https://img.shields.io/pypi/v/datadm)](https://pypi.org/project/datadm/)
[![tests](https://github.com/approximatelabs/datadm/actions/workflows/test-build-publish.yml/badge.svg)](https://github.com/approximatelabs/datadm/actions/workflows/test-build-publish.yml)
[![Open In Colab](https://colab.research.google.com/assets/colab-badge.svg)](https://colab.research.google.com/github/approximatelabs/datadm/blob/main/datadm.ipynb)
[![](https://dcbadge.vercel.app/api/server/kW9nBQErGe?compact=true&style=flat)](https://discord.gg/kW9nBQErGe)

![dataDM](datadm-header.png?raw=true)

DataDM is your private data assistant. A conversational interface for your data where you can load, clean, transform, and visualize without a single line of code. DataDM is open source and can be run entirely locally, keeping your juicy data secrets fully private.

## Demo

https://github.com/approximatelabs/datadm/assets/916073/f15e6ab5-8108-40ea-a6de-c69a1389af84

Note: Demo above is `GPT-4`, which sends the conversation to OpenAI's API. To use in full local mode, be sure to select `starchat-alpha-cuda` or `starchat-beta-cuda` as the model. This will use the StarChat model, which is a bit less capable but runs entirely locally.

⚠️ LLMs are known to hallucinate and generate fake results. So, double-check before trusting their results blindly!

### ⇒ *[Try it now! Hosted public environment is live! (Click Here)](https://datadm.approx.dev/new)* ⇐
Don't put any sensitive data in the public environment, use the docker image or colab notebook for your private conversations.

### Join our [discord](https://discord.gg/kW9nBQErGe) to join the community and share your thoughts!

## Features
- [x] Persistent Juptyer kernel backend for data manipulation during conversation
- [x] Run entirely locally, keeping your data private
- [x] Natural language chat, visualizations/plots, and direct download of data assets
- [x] Easy to use docker-images for one-line deployment
- [x] Load multiple tables directly into the chat
- [x] Search for data and load CSVs directly from github
- [x] Option to use OpenAI's GPT-3.5 or GPT-4 (requires API key)
- [ ] WIP: GGML based mode (CPU only, no GPU required)
- [ ] WIP: Rollback kernel state when undo ~using `criu`~ (re-execute all cells)
- [ ] TODO: Support for more data sources (e.g. SQL, S3, PySpark etc.)
- [ ] TODO: Export a conversation as a notebook or html

## Things you can ask DataDM
- [x] Load data from a URL
- [x] Clean data by removing duplicates, nulls, outliers, etc.
- [x] Join data from multiple tables into a single output table
- [x] Visualize data with plots and charts
- [x] Ask whatever you want to your very own private code-interpreter

## Quickstart

You can use docker, colab, or install locally.

### 1. Docker to run locally
```bash
docker run -e OPENAI_API_KEY={{YOUR_API_KEY_HERE}} -p 7860:7860 -it ghcr.io/approximatelabs/datadm:latest
```

For local-mode using StarChat model (requiring a CUDA device with at least 24GB of RAM)
```bash
docker run --gpus all -p 7860:7860 -it ghcr.io/approximatelabs/datadm:latest-cuda
```

### 2. Colab to run in the cloud
[![Open In Colab](https://colab.research.google.com/assets/colab-badge.svg)](https://colab.research.google.com/github/approximatelabs/datadm/blob/main/datadm.ipynb)


### 3. Use as a python package

> ⚠️ datadm used this way runs LLM generated code in your userspace

For local-data, cloud-model mode (no GPU required) - requires an OpenAI API key
```bash
$ pip install datadm
$ datadm
```

For local-mode using StarChat model (requiring a CUDA device with at least 24GB of RAM)
```bash
$ pip install "datadm[cuda]"
$ datadm
```

## Special Thanks

* [starchat-beta](https://huggingface.co/HuggingFaceH4/starchat-beta) ([starcoder](https://github.com/bigcode-project/starcoder) with [databricks-dolly](https://huggingface.co/datasets/databricks/databricks-dolly-15k) and [OpenAssistant/oasst1](https://huggingface.co/datasets/OpenAssistant/oasst1))
* [Guidance](https://github.com/microsoft/guidance)
* [HuggingFace](https://huggingface.co/)
* [OpenAI](https://openai.com/)

## Contributions

Contributions are welcome! Feel free to submit a PR or open an issue.

## Community

Join the [Discord](https://discord.gg/kW9nBQErGe) to chat with the team

Check out our other projects: [sketch](https://github.com/approximatelabs/sketch) and [approximatelabs](https://approximatelabs.com)

Business Centric Workflow

Secure and Efficient Commodity Exposure Data Distribution with Multi-Layered Validation
This 7-step process ensures the accurate and timely distribution of commodity exposure data to our clients through APIs. Our robust validation framework minimizes errors and guarantees data integrity throughout the journey.
1. Secure Acquisition: Data is securely retrieved from the designated Aladdin S3 bucket, acting as our single source of truth.
2. Pre-Validation - First Line of Defense: This initial checkpoint verifies file availability and completeness. It checks for the presence of a valid effective date and ensures the data adheres to the expected format. Any inconsistencies trigger Exception Handling I, preventing potentially faulty data from entering the system.
3. Exception Handling I - Proactive Issue Resolution: If pre-validation flags an issue, the data is quarantined for review by specialized teams in Run Org or Product Ops. The root cause of non-compliance is identified and addressed before the data is re-routed back to Acquisition for a second attempt at pre-validation. This proactive approach prevents errors from propagating further and ensures only compliant data enters the processing pipeline.
4. ETL - Building the Foundation: This stage involves extracting data from its source, transforming it into a format suitable for our system, and loading it into the designated EIP-N database table. This process lays the foundation for accurate data analysis and distribution.
5. Post-Validation - Advanced Data Quality Checks: After ETL, our robust anomaly detection system meticulously scans for potential errors or irregularities within the data itself. This multi-layered validation approach offers an extra layer of security, safeguarding data integrity before distribution. If anomalies are discovered, the data is quarantined in a dedicated S3 bucket (Quarantine S3) for further investigation.
6. Exception Handling II - Ensuring Compliance: Data that passes post-validation undergoes a final compliance check. This ensures the data adheres to all relevant business rules and regulations. Any non-compliant data is flagged and routed back to Product Ops. Here, our dedicated Exposures team collaborates with Product Ops to rectify the data in the tables. Once corrected, the data re-enters the process for anomaly detection in post-validation. This two-stage exception handling process guarantees complete data accuracy and compliance.
7. Secure Distribution: Upon successful completion of all validation stages, the data is deemed fit for distribution. Authorized clients can access the data seamlessly through our secure APIs, including REST and GraphQL APIs. This ensures timely access to accurate and reliable commodity exposure data.
Process Improvement Opportunities:
1. Automate Exception Handling: Investigate potential automation for resolving common pre-validation and compliance check failures. This could streamline the process and reduce reliance on manual intervention.
2. Real-time Data Monitoring: Implement real-time data monitoring to proactively identify potential issues within the Aladdin S3 bucket or data source. This can help prevent errors from entering the system altogether.
3. Data Lineage Tracking: Consider incorporating data lineage tracking to map the data flow throughout the process. This enhances transparency and simplifies troubleshooting in case of any issues.


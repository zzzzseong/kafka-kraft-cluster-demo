# KAFKA-KRAFT-CLUSTER-DEMO

***

<img src="/images/kafka-description.png" width=934 alt="kafka-description">

## Architecture

<img src="/images/kafka-architecture.png" width="934" alt="kafka-architecture">

## Kafka Cluster File Tree
Local 환경에서 구성했기 떄문에 각각의 서버는 다음과 같은 구조로 구성되어 있다. 

```bash
.
├── kafka(3.7.0)-server-1
│   ├── bin
│   │   └── windows
│   ├── config
│   │   └── kraft
│   ├── libs
│   ├── licenses
│   ├── logs
│   │   └── kraft-combined-logs
│   └── site-docs
├── kafka(3.7.0)-server-2
│   ├── bin
│   │   └── windows
│   ├── config
│   │   └── kraft
│   ├── libs
│   ├── licenses
│   ├── logs
│   │   └── kraft-combined-logs
│   └── site-docs
└── kafka(3.7.0)-server-3
    ├── bin
    │   └── windows
    ├── config
    │   └── kraft
    ├── libs
    ├── licenses
    ├── logs
    │   └── kraft-combined-logs
    └── site-docs
```

## Kafka-UI
https://github.com/provectus/kafka-ui
